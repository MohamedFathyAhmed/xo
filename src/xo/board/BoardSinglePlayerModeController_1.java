/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xo.board.game.GameHandler;

/**
 *
 * @author Marina
 */
public class BoardSinglePlayerModeController_1 extends BoardMultiPlayerModeController {

    private GameHandler gameHandler;
    private boolean isPc = false;
    int locationPcPlay;

    public BoardSinglePlayerModeController_1(Stage stage) {
        super(stage);
        gameHandler = new GameHandler(this::handleGameState);

    }
      @Override
    protected void boardButtonExited(MouseEvent event) {
        ((Button) event.getSource()).getStyleClass().remove(boardHoverStyleClasses.get());
    }

    @Override
    protected void boardButtonEntered(MouseEvent event) {
        ((Button) event.getSource()).getStyleClass().add(boardHoverStyleClasses.get());
    }

    @Override
    protected void boardButtonClicked(ActionEvent event) {

        super.boardButtonClicked(event);
        Button button = (Button) event.getSource();
        button.setDisable(true);
        applyStyleClass(button);
        nextTurn();
        gameHandler.play(((Button) event.getSource()).getId().charAt(6) - '0', gameShapes.get());

            getPlayFromPc();
  
    }

    void getPlayFromPc() {
    
        String BoardChar = String.valueOf(gameHandler.getBoardChars());
        EasyAi pcAi = new EasyAi(BoardChar);
        locationPcPlay = pcAi.res;
        nextTurn();
        applyStyleClass(boardButtons[locationPcPlay]);
        boardButtons[pcAi.res].setDisable(true);
        nextTurn();
        System.out.println("locationPcPlay =======>" + locationPcPlay+"------>"+BoardChar);
        gameHandler.play(locationPcPlay, gameShapes.get());
        
         
         
    }
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
    }

    @Override
    protected void applyStyleClass(Button button) {
        button.getStyleClass().add(boardStyleClasses.get());
    }
}


