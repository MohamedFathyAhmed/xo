/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xo.board.game;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

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
    
    
public static void easyMode(String board) {
        int emtyLocation[];
        emtyLocation = new int[0];
        int arrNum = 0;
        for (int i = 0; i < 9; i++) {

            if (board.charAt(i) == '-') {
               emtyLocation = append(emtyLocation, i);
                arrNum++;
            }
        }
        int randNum = getRandomElement(emtyLocation);
        System.out.println(randNum);

    }

    public static int getRandomElement(int[] arr) {
        return arr[ThreadLocalRandom.current().nextInt(arr.length)];
    }
    
      static <T> int[] append(int[] arr, int element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

}


 