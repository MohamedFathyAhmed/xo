/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.database;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author mohamed
 */
public class DataAccessLayer {

    private static Connection connection;
    private static ResultSet record;
    
    public void connect() throws SQLException {
        connection = TicTacToeDatabase.getInstance().getConnection();
    }
    
   
    
    public void disconnect() throws SQLException{
       TicTacToeDatabase.getInstance().disconnect();
    }

}
