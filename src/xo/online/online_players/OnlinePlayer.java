/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo.online.online_players;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mohamed
 */
public class OnlinePlayer {



    private SimpleStringProperty username;

    public OnlinePlayer(String username) {
        this.username = new SimpleStringProperty(username);
    }

    public OnlinePlayer() {
        this("");
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }


}
