/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.handlers;

import data.database.models.Game;
import data.database.models.Play;

/**
 *
 * @author Marina
 */
public class ResponseHandler {
    //"login/false/username or password incorrect"
    Response handle(String response) {
        String[] splitedResponse = response.split(RequestTypeConstants.MESSAGE_SPLITER);
        switch (splitedResponse[0]) {
            case RequestTypeConstants.SIGNIN:
            case RequestTypeConstants.SIGNUP:
            case RequestTypeConstants.LOGOUT:
            case RequestTypeConstants.REQUEST_GAME:
            case RequestTypeConstants.PLAYAGAIN:
            case RequestTypeConstants.LEAVE:
            case RequestTypeConstants.SHAPE:
            case RequestTypeConstants.RECORD:
                return new Response(splitedResponse[1], splitedResponse[2]);
            case RequestTypeConstants.RECORDED_GAMES_LIST:
                return new RececordedGameListResponse(
                        gamesSpliter(splitedResponse[3]),
                        splitedResponse[1],
                        splitedResponse[2]
                );
            case RequestTypeConstants.RECORDED_GAME:
                return new RececordedGameResponse(
                        playSpliter(splitedResponse[3]),
                        splitedResponse[1],
                        splitedResponse[2]);
            case RequestTypeConstants.HISTORY:
                return new HistoryResponse(
                        gamesSpliter(splitedResponse[3]),
                        splitedResponse[1],
                        splitedResponse[2]);
            case RequestTypeConstants.ONLINE_PLAYERS:
                return new OnlinePlayersResponse(playersSpliter(splitedResponse[3]),
                        splitedResponse[1],
                        splitedResponse[2]
                );
        }
        return new Response("false", "not response");
    }

    private Game[] gamesSpliter(String gameString) {
        String[] gamesString = gameString.split(RequestTypeConstants.ARRAY_SPLITER);
        String[] gameAttributes;
        Game[] games = new Game[gamesString.length];
        for (int gameIndex = 0; gameIndex < games.length; gameIndex++) {
            gameAttributes = gamesString[gameIndex].split(RequestTypeConstants.OBJECT_SPLITER);
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
        String[] playsArrayString = playsString.split(RequestTypeConstants.ARRAY_SPLITER);
        String[] playAttributes;
        Play[] plays = new Play[playsArrayString.length];
        for (int playIndex = 0; playIndex < plays.length; playIndex++) {
            playAttributes = playsArrayString[playIndex].split(RequestTypeConstants.OBJECT_SPLITER);
            plays[playIndex] = new Play(
                    playAttributes[0],
                    playAttributes[1],
                    playAttributes[2],
                    playAttributes[3]);
        }
        return plays;
    }

    private String[] playersSpliter(String playersString) {
        String[] playersArray = playersString.split(RequestTypeConstants.ARRAY_SPLITER);
        return playersArray;
    }

}






