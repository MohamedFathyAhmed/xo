/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers;

import xo.online.handlers.responses.InfoResponse;
import xo.online.handlers.responses.OnlinePlayersResponse;
import xo.online.handlers.responses.HistoryResponse;
import xo.online.handlers.responses.GameRequestResponse;
import xo.online.handlers.responses.RececordedGameResponse;
import xo.online.handlers.responses.Response;
import data.database.models.Game;
import data.database.models.Play;
import java.util.ArrayList;
import java.util.List;
import xo.online.handlers.responses.AuthResponse;
import xo.online.handlers.responses.GameDoneResponse;
import xo.online.handlers.responses.LeaveResponse;
import xo.online.handlers.responses.PlayResponse;
import xo.online.handlers.responses.RecordResponse;
import xo.online.handlers.responses.RequestGameAnswerResponse;

/**
 *
 * @author Marina
 */
public class ResponseHandler {

    //"login/false/username or password incorrect"
    Response handle(String response) {
        String[] splitedResponse = response.split(RequestType.MESSAGE_SPLITER);
        switch (splitedResponse[0]) {
            case RequestType.SIGNIN:
            case RequestType.SIGNUP:
                return new AuthResponse(splitedResponse[1], splitedResponse[2]);

            case RequestType.LOGOUT:
            case RequestType.PLAYAGAIN:
            case RequestType.SHAPE:
            case RequestType.CONNECTED:

            case RequestType.LEAVE:
                return new LeaveResponse();
            case RequestType.RECORD:
                return new RecordResponse(splitedResponse[1]);

            case RequestType.RECEIVER_REQUEST_GAME_ANSWER:
            case RequestType.SENDER_REQUEST_GAME_ANSWER:
                return new InfoResponse(splitedResponse[1]);

            case RequestType.REQUEST_GAME_ANSWER:
                return new RequestGameAnswerResponse(splitedResponse[1]);

            case RequestType.REQUEST_GAME:
                return new GameRequestResponse(splitedResponse[1]);

            case RequestType.GAME_DONE:
                return new GameDoneResponse(splitedResponse[1]);

            case RequestType.RECORDED_GAMES_LIST:
                return new HistoryResponse(gamesSpliter(splitedResponse[3]));

            case RequestType.PLAY:
                return new PlayResponse(splitedResponse[1], splitedResponse[2]);

            case RequestType.RECORDED_GAME:
                return new RececordedGameResponse(
                        playSpliter(splitedResponse[1]));

            case RequestType.HISTORY:
                return new HistoryResponse(gamesSpliter(splitedResponse[1]));

            case RequestType.ONLINE_PLAYERS:
                if (splitedResponse.length != 1) {
                    return new OnlinePlayersResponse(playersSpliter(splitedResponse[1]));
                } else {
                    return new OnlinePlayersResponse(new String[]{});
                }
        }
        return new InfoResponse("false");
    }

    private Game[] gamesSpliter(String gameString) {
        String[] gamesString = gameString.split(RequestType.ARRAY_SPLITER);
        String[] gameAttributes;
        Game[] games = new Game[gamesString.length];
        for (int gameIndex = 0; gameIndex < games.length; gameIndex++) {
            gameAttributes = gamesString[gameIndex].split(RequestType.OBJECT_SPLITER);
            games[gameIndex] = new Game(
                    gameAttributes[0],
                    gameAttributes[1],
                    gameAttributes[2],
                    gameAttributes[3],
                    gameAttributes[4],
                    gameAttributes[5],
                    gameAttributes[6],
                    gameAttributes[7]
            );
        }
        return games;
    }

    private List<Play> playSpliter(String playsString) {
        String[] playsArrayString = playsString.split(RequestType.ARRAY_SPLITER);
        String[] playAttributes;
        List<Play> plays = new ArrayList();
        for (int playIndex = 0; playIndex < playsArrayString.length; playIndex++) {
            playAttributes = playsArrayString[playIndex].split(RequestType.OBJECT_SPLITER);
            plays.add(new Play(
                    playAttributes[0],
                    playAttributes[1]));
        }
        return plays;
    }

    private String[] playersSpliter(String playersString) {
        String[] playersArray = playersString.split(RequestType.ARRAY_SPLITER);
        return playersArray;
    }

}
