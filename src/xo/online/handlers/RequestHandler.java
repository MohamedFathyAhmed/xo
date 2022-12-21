/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers;

import xo.online.handlers.responses.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Consumer;

/**
 *
 * @author Marina
 */
public class RequestHandler implements Runnable {

    private String ipAddress;
    private int port;
    private static RequestHandler instance;
    private final ResponseHandler responseHandler;
    private final RequestCreator requestCreator;
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private boolean isRunning;
    private Consumer<Response> responseReceiver;
    private ErrorMessageSender errorMessageSender;

    private RequestHandler() {
        responseHandler = new ResponseHandler();
        requestCreator = new RequestCreator();
    }

    public static RequestHandler getInstance(
            Consumer<Response> responseReceiver,
            ErrorMessageSender errorMessageSender,
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

    public static RequestHandler getInstance(
            Consumer<Response> responseReceiver,
            int port,
            String ipAddress
    ) {
        if (instance == null) {
            instance = new RequestHandler();
        }
        instance.responseReceiver = responseReceiver;
        instance.port = port;
        instance.ipAddress = ipAddress;
        return instance;
    }

    public static RequestHandler getInstance(
            Consumer<Response> responseReceiver,
            ErrorMessageSender errorMessageSender
    ) {
        if (instance == null) {
            instance = new RequestHandler();
        }
        instance.responseReceiver = responseReceiver;
        instance.errorMessageSender = errorMessageSender;
        return instance;
    }

    public static RequestHandler getInstance(
            Consumer<Response> responseReceiver
    ) {
        if (instance == null) {
            instance = new RequestHandler();
        }
        instance.responseReceiver = null;
        instance.responseReceiver = responseReceiver;
        return instance;
    }

    public static RequestHandler getInstance(
            ErrorMessageSender errorMessageSender
    ) {
        if (instance == null) {
            instance = new RequestHandler();
        }
        instance.errorMessageSender = errorMessageSender;
        return instance;
    }

    public static RequestHandler getInstance(int port,
            String ipAddress,
            ErrorMessageSender errorMessageSender) {
        if (instance == null) {
            instance = new RequestHandler();
        }
        instance.port = port;
        instance.ipAddress = ipAddress;
        instance.errorMessageSender = errorMessageSender;
        return instance;
    }

    public void create(String type, ParamterizeRequest request) throws IOException {
        request(requestCreator.create(type, request));
    }

    public void create(String type, Request request) throws IOException {
        request(requestCreator.create(type, request));
    }

    public void create(String type) throws IOException {
        request(requestCreator.create(type));
    }

    private void request(String request) throws IOException {
        printWriter.println(request);
    }

    public void disconnect() throws IOException {
        if (isRunning) {
            isRunning = false;
            printWriter.close();
            bufferedReader.close();
            socket.close();
        }
    }

    public void connect() throws IOException {
        if (!isRunning) {
            socket = new Socket(ipAddress, port);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            isRunning = true;
            new Thread(this).start();
        }
    }

    public void connect(int port, String ipAddress) throws IOException {
        if (!isRunning) {
            instance.port = port;
            instance.ipAddress = ipAddress;
            socket = new Socket(ipAddress, port);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            isRunning = true;
            new Thread(this).start();
        }
    }

    public boolean isConnected() {
        return isRunning;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Response response = responseHandler.handle(bufferedReader.readLine());
                responseReceiver.accept(response);
            } catch (IOException ex) {
                errorMessageSender.accept(ex.getMessage());
            } catch (NullPointerException ex1) {
                try {
                    disconnect();
                    socket = null;
                } catch (IOException ex2) {
                    errorMessageSender.accept(ex2.getMessage());
                }
                errorMessageSender.accept(ex1.getMessage());
            }
        }
    }

    class RequestCreator {

        String create(String type, ParamterizeRequest request) {
            return type
                    + RequestType.MESSAGE_SPLITER
                    + request.getName()
                    + RequestType.MESSAGE_SPLITER
                    + request.getParamter();
        }

        String create(String type, Request request) {
            return type
                    + RequestType.MESSAGE_SPLITER
                    + request.getName();
        }

        String create(String type) {
            return type;

        }

    }
}
