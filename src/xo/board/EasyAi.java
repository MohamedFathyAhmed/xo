package xo.board;


import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author mo_fathy
 */
public class EasyAi {

    static String board;
    static int res;

    
     public EasyAi() {
    
    }

    public EasyAi(String board) {
        this.board = board;
         easyMode(board);
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
        
        res = randNum;
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
    
    
    public static void setBoard(String board) {
        EasyAi.board = board;
        easyMode(board);
    }

    public static int getRes() {
        return res;
    }
    
}
