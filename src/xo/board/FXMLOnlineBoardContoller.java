package xo.board;

import xo.online.handlers.responses.Response;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import java.io.IOException;
import xo.online.handlers.Request;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.util.Collection;
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
import xo.online.handlers.RequestHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import xo.online.handlers.responses.LeaveResponse;
import xo.online.handlers.responses.RecordResponse;

public class FXMLOnlineBoardContoller extends FXMLBoardController {

    @FXML
    private ImageView player1OImageView;
    @FXML
    private ImageView player1XImageView;
    @FXML
    private ImageView player2OImageView;
    @FXML
    private ImageView player2XImageView;
    private final RequestHandler requestHandler;
    private ArrayList<Button> freeToPlayBoardButtons;

    public FXMLOnlineBoardContoller(final Stage stage) {
        super(stage);
        this.requestHandler = RequestHandler.getInstance((Response response) -> {
            if (response instanceof PlayResponse) {
                this.performPlayOnBoard(((PlayResponse) response).getPosition());
                this.disableBoardButtons(!((PlayResponse) response).isMyTurn());
            } else if (response instanceof GameDoneResponse) {
                Platform.runLater(() -> handleGameState(((GameDoneResponse) response).getGameState()));
            } else if (response instanceof LeaveResponse) {
                TicTacToeNavigator.previousLater(stage);
            } else if (response instanceof RecordResponse) {
                this.recordingButton.fire();
            }
        });
    }

    public void initialize(final URL url, final ResourceBundle rb) {
        super.initialize(url, rb);
        final boolean isMyTurnFirst = this.currentGameData.getOnlineName().equals(this.currentGameData.getPlayer1());
        this.disableBoardButtons(!isMyTurnFirst);
        if (!isMyTurnFirst) {
            this.boardHoverStyleClasses.next();
            this.updateLeftSidePlayer();
        }
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
        this.disableBoardButtons(true);
    }

    protected void disableBoardButtons(final boolean disabled) {
        if (this.freeToPlayBoardButtons != null) {
            this.freeToPlayBoardButtons.forEach(button -> button.setDisable(disabled));
        } else {
            super.disableBoardButtons(disabled);
        }
    }

    protected void leaveButtonClicked(final ActionEvent event) {
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

    private void updateLeftSidePlayer() {
        this.player1NameText.setText(this.currentGameData.getPlayer2());
        this.player2NameText.setText(this.currentGameData.getPlayer1());
        final Paint tempPaint = this.player1NameText.getFill();
        this.player1NameText.setFill(this.player2NameText.getFill());
        this.player2NameText.setFill(tempPaint);
        Image tempImage = this.player2OImageView.getImage();
        this.player2OImageView.setImage(this.player1OImageView.getImage());
        this.player1OImageView.setImage(tempImage);
        tempImage = this.player2XImageView.getImage();
        this.player2XImageView.setImage(this.player1XImageView.getImage());
        this.player1XImageView.setImage(tempImage);
    }
}
