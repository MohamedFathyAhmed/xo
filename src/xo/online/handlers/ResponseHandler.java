/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers;

import xo.online.handlers.responses.InfoResponse;
import xo.online.handlers.responses.OnlinePlayersResponse;
import xo.online.handlers.responses.HistoryResponse;
import xo.online.handlers.responses.RececordedGameListResponse;
import xo.online.handlers.responses.GameRequestResponse;
import xo.online.handlers.responses.RececordedGameResponse;
import xo.online.handlers.responses.Response;
import data.database.models.Game;
import data.database.models.Play;
import xo.online.handlers.responses.GameDoneResponse;
import xo.online.handlers.responses.PlayResponse;

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
            case RequestType.LOGOUT:
            case RequestType.PLAYAGAIN:
            case RequestType.LEAVE:
            case RequestType.SHAPE:
            case RequestType.RECORD:
            case RequestType.CONNECTED:
            case RequestType.REQUEST_GAME_ANSWER:
                return new Response(splitedResponse[1], splitedResponse[2]);

            case RequestType.RECEIVER_REQUEST_GAME_ANSWER:
            case RequestType.SENDER_REQUEST_GAME_ANSWER:
                return new InfoResponse(splitedResponse[1], splitedResponse[2]);

            case RequestType.REQUEST_GAME:
                return new GameRequestResponse(splitedResponse[3], splitedResponse[1], splitedResponse[2]);

            case RequestType.GAME_DONE:
                return new GameDoneResponse(splitedResponse[2], splitedResponse[1], splitedResponse[3]);

            case RequestType.RECORDED_GAMES_LIST:
                return new RececordedGameListResponse(
                        gamesSpliter(splitedResponse[3]),
                        splitedResponse[1],
                        splitedResponse[2]
                );

            case RequestType.PLAY:
                return new PlayResponse(splitedResponse[1],
                        splitedResponse[2],
                        splitedResponse[3]);

            case RequestType.REDIRECT_PLAY:
                return new PlayResponse(splitedResponse[1],
                        splitedResponse[2],
                        splitedResponse[3]);

            case RequestType.RECORDED_GAME:
                return new RececordedGameResponse(
                        playSpliter(splitedResponse[3]),
                        splitedResponse[1],
                        splitedResponse[2]);

            case RequestType.HISTORY:
                return new HistoryResponse(
                        gamesSpliter(splitedResponse[3]),
                        splitedResponse[1],
                        splitedResponse[2]);

            case RequestType.ONLINE_PLAYERS:
                if (splitedResponse.length == 4) {
                    return new OnlinePlayersResponse(playersSpliter(splitedResponse[3]),
                            splitedResponse[1],
                            splitedResponse[2]
                    );
                } else {
                    return new OnlinePlayersResponse(new String[]{},
                            splitedResponse[1],
                            splitedResponse[2]
                    );
                }
        }
        return new Response("false", "not response");
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
                    gameAttributes[5]);
        }
        return games;
    }

    private Play[] playSpliter(String playsString) {
        String[] playsArrayString = playsString.split(RequestType.ARRAY_SPLITER);
        String[] playAttributes;
        Play[] plays = new Play[playsArrayString.length - 1];
        for (int playIndex = 0; playIndex < plays.length; playIndex++) {
            playAttributes = playsArrayString[playIndex].split(RequestType.OBJECT_SPLITER);
            plays[playIndex] = new Play(
                    playAttributes[0],
                    playAttributes[1],
                    playAttributes[2],
                    playAttributes[3]);
        }
        return plays;
    }

    private String[] playersSpliter(String playersString) {
        String[] playersArray = playersString.split(RequestType.ARRAY_SPLITER);
        return playersArray;
    }

}
