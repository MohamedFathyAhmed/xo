package xo.board;

import data.GameShape;
import xo.online.handlers.responses.Response;
import java.io.IOException;
import xo.online.handlers.Request;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.net.URL;
import xo.utlis.TicTacToeNavigator;
import javafx.application.Platform;
import xo.online.handlers.responses.GameDoneResponse;
import xo.online.handlers.responses.PlayResponse;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.ArrayList;
import xo.board.game.GameState;
import xo.online.handlers.RequestHandler;
import xo.online.handlers.responses.LeaveResponse;
import xo.online.handlers.responses.RecordResponse;

public class FXMLOnlineBoardContoller extends FXMLBoardController {

    private final RequestHandler requestHandler;
    private ArrayList<Button> freeToPlayBoardButtons;

    public FXMLOnlineBoardContoller(final Stage stage) {
        super(stage);
        this.requestHandler = RequestHandler.getInstance((Response response) -> {
            if (response instanceof PlayResponse) {
                this.performPlayOnBoard(((PlayResponse) response).getPosition());
                this.disableBoard(!((PlayResponse) response).isMyTurn());
            } else if (response instanceof GameDoneResponse) {
                Platform.runLater(() -> handleGameState(((GameDoneResponse) response).getGameState()));
            } else if (response instanceof LeaveResponse) {
                TicTacToeNavigator.previousLater(stage);
            } else if (response instanceof RecordResponse) {
                Platform.runLater(() -> super.recordButtonClicked(new ActionEvent()));
            }
        });
    }

    @Override
    void handleGameState(GameState gameState) {
        switch (gameState) {
            case PLAYER_ONE_WON:
                currentGameData.setWinnerPlayer(currentGameData.getPlayer1());
                 showWinnerDialog();
                break;
                 case PLAYER_TWO_WON:
                    currentGameData.setWinnerPlayer(currentGameData.getPlayer2());
                     showWinnerDialog();
                break;
                  case DRAW:
                
                break;
            default:
                throw new AssertionError();
        }
    }

    
    public void initialize(final URL url, final ResourceBundle rb) {
        final boolean isMyTurnFirst = this.currentGameData.getOnlineName().equals(this.currentGameData.getPlayer1());
        this.disableBoard(!isMyTurnFirst);
        if (!isMyTurnFirst) {
            boardHoverStyleClasses.next();
            currentGameData.setPlayer1Shape(GameShape.O);
            currentGameData.setPlayer2(currentGameData.getPlayer1());
            currentGameData.setPlayer1(currentGameData.getOnlineName());
        }
        super.initialize(url, rb);
        this.freeToPlayBoardButtons = new ArrayList<Button>(Arrays.asList(this.boardButtons));
    }

    protected void boardButtonEntered(final MouseEvent event) {
        ((Button) event.getSource()).getStyleClass().add(this.boardHoverStyleClasses.get());
    }

    protected void boardButtonClicked(final ActionEvent event) {
        final int position = ((Button) event.getSource()).getId().charAt(6) - '0';
        try {
            this.requestHandler.create("play", new Request(position + ""));
        } catch (IOException ex) {
        }
    }

    protected void boardButtonExited(final MouseEvent event) {
        ((Button) event.getSource()).getStyleClass().remove(this.boardHoverStyleClasses.get());
    }

    protected void applyStyleClass(final Button button) {
        button.getStyleClass().add(this.boardStyleClasses.get());
    }

    protected void performPlayOnBoard(final int position) {
        final Button button = this.boardButtons[position];
        this.applyStyleClass(button);
        button.setDisable(true);
        this.freeToPlayBoardButtons.remove(button);
        this.nextTurn();
    }

    protected void nextTurn() {
        this.boardStyleClasses.next();
        boardGridPane.setDisable(true);
    }

    protected void disableBoard(final boolean disabled) {
        super.disableBoard(disabled);
    }

    protected void leaveButtonClicked(final ActionEvent event) throws IOException {
        try {
            this.requestHandler.create("leave");
        } catch (IOException ex) {
        }
        super.leaveButtonClicked(event);
    }

    protected void recordButtonClicked(final ActionEvent event) {
        try {
            this.requestHandler.create("record");
        } catch (IOException ex) {
        }
    }

}
