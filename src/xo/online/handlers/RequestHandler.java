/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.function.Consumer;

/**
 *
 * @author Marina
 */
public class RequestHandler implements Runnable {

    private static RequestHandler instance;
    private final ResponseHandler responseHandler;
    private final RequestCreator requestCreator;
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private boolean isRunning;
    private int port;
    private String ipAddress;
    private Consumer<Response> responseReceiver;
    private Consumer<String> errorMessageSender;

    private RequestHandler() {
        responseHandler = new ResponseHandler();
        requestCreator = new RequestCreator();
    }

    public static RequestHandler getInstance(
            Consumer<Response> responseReceiver,
            Consumer<String> errorMessageSender,
            int port,
            String ipAddress
    ) {
        if (instance == null) {
            instance = new RequestHandler();
        }
        instance.responseReceiver = responseReceiver;
        instance.port = port;
        instance.ipAddress = ipAddress;
        instance.errorMessageSender = errorMessageSender;
        return instance;
    }

    public RequestCreator getRequestCreator() {
        return requestCreator;
    }

    public void request(String request) throws IOException {
        bufferedWriter.write(request);
    }

    public void disconnect() throws IOException {
        isRunning = false;
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
    }

    public void connect() throws IOException {
        isRunning = true;
        socket = new Socket(ipAddress, port);
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                responseReceiver.accept(responseHandler.handle(bufferedReader.readLine()));
            } catch (IOException ex) {
                errorMessageSender.accept(ex.getMessage());
                try {
                    disconnect();
                } catch (IOException ex1) {
                    errorMessageSender.accept(ex1.getMessage());
                }
                socket = null;
            }
        }
    }

    class RequestCreator {

        String create(RequestTypeConstants type, ParamterizeRequest request) {
            return type
                    + RequestTypeConstants.MESSAGE_SPLITER
                    + request.getName()
                    + RequestTypeConstants.MESSAGE_SPLITER
                    + request.getParamter()
                    + RequestTypeConstants.MESSAGE_SPLITER;
        }

        String create(RequestTypeConstants type, Request request) {
            return type
                    + RequestTypeConstants.MESSAGE_SPLITER
                    + request.getName()
                    + RequestTypeConstants.MESSAGE_SPLITER;
        }

    }
}
