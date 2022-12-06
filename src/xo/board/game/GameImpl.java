/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xo.board.game;

/**
 *
 * @author mo_fathy
 */
public class GameImpl implements Game{
      String xPattern = "(^(x..x..x..)|(.x..x..x.)|(..x..x..x)|(xxx......)|(...xxx...)|(......xxx)|(..x.x.x..)|(x...x...x)$)";
       String oPattern;
       GameImpl(){
           oPattern=xPattern.replaceAll("x", "o");
       }
    
    
    public GameState isGameRunning(String board) {
        boolean resultX = board.matches(xPattern);
        boolean resultO = board.matches(oPattern);
   
        if(resultX){
            return GameState.PLAYER_ONE_WON;
        }else if(resultO){
            return GameState.PLAYER_TWO_WON;
        }else if( board.contains("-"))
          return GameState.ONGOING;
        else{
        return GameState.DRAW;
        }
    }
    
    
}
