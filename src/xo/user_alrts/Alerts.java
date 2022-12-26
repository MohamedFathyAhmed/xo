/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.user_alrts;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Marina
 */
public class Alerts extends Alert {

    private static final String userUnFoundMessage = "This User Not Founded";
    private static final String recordUnfoundMessage = "This Game Not Recorded";
    private static final String wrongPortMessage = "This Port is Wrong";
    private static final String wongIPFoundMessage = "This IP Wrong";
    private static final String historyNotFoundMessage = "Thir is No History Yet";
    private static final String leaveMessage = "You will leave the Game, Are You Sure?";
    private static final String gameRecordedMessage = "Start Recording";
    ////////////////////////////////////// worning
    private static final String nameFoundMessage = "Please Enter Your Name";
    private static final String passwordFoundMessage = "Please Enter Your Password";
    private static final String namePlayer1FoundMessage = "Please Enter Player One Name";
    private static final String namePlayer2FoundMessage = "Please Enter Player Two Your Name";

    /*
    private static final String gameRecordedMessage ="Start Recording";
    private static final String gameRecordedMessage ="Start Recording";
    private static final String gameRecordedMessage ="Start Recording";
    private static final String gameRecordedMessage ="Start Recording";
    private static final String gameRecordedMessage ="Start Recording";
    private static final String gameRecordedMessage ="Start Recording";
     */

    public Alerts() {
        super(AlertType.CONFIRMATION);
    }

    public Alert displayAlert(String message, AlertType type,AlertButtonResult res) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Tic Tac Toe");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.APPLY
                || result.get() == ButtonType.OK
                || result.get() == ButtonType.YES) {
            res.IfOk();
            
        }

        if (result.get() == ButtonType.CLOSE
                || result.get() == ButtonType.CANCEL
                || result.get() == ButtonType.FINISH
                || result.get() == ButtonType.NO) {
            res.IfCancel();
        }

        return alert;

    }

}
