package xo.history;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import xo.online.handlers.RequestHandler;
import xo.online.handlers.RequestType;
import xo.online.handlers.responses.HistoryResponse;
import xo.online.handlers.responses.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mohamed
 */
public class FXMLOnlineHistoryController extends FXMLHistoryController {

    RequestHandler requestHandler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);

        requestHandler = RequestHandler.getInstance((Response response) -> {
            if (response instanceof HistoryResponse) {
                updateTableViewItems(((HistoryResponse) response).getGames());

            }
        });

        try {
            requestHandler.create(RequestType.HISTORY);
        } catch (IOException ex) {

        }
    }

}
