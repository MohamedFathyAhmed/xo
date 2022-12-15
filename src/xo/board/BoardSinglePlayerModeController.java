/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.board;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Marina
 */
public class BoardSinglePlayerModeController extends BoardMultiPlayerModeController {

    private boolean isPc = false;

    public BoardSinglePlayerModeController(Stage stage) {
        super(stage);
    }

    @Override
    protected void boardButtonClicked(ActionEvent event) {
        super.boardButtonClicked(event);
        boardGridPane.setDisable(!isPc);
        isPc = !isPc;
    }

    }
