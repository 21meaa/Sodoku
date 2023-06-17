package de.hft_stuttgart.ip1.server;

import java.rmi.RemoteException;
import java.util.Random;

public class newGame {


    static Random ran = new Random();
    static int size = 25;

    public static void main(String[] args) {



        int[][] grid = new int[size][size];
        String[] symbols = generateSymbols(size);
        fillDiagonal(grid, symbols);

        for (int[] a: grid){
            for (int b: a){

                if(b > 9){
                    System.out.print(getSymbol(b));
                }else {
                    System.out.print(b);
                }

            }
            System.out.println();
        }

    }

    public static void fillDiagonal(int[][] grid, String[] symbols){
        String[] symbol = symbols;
        for(int i = 0; i < grid.length; i++){

            grid[i][i] = getSymbolValue(symbols[ran.nextInt(size)]);
        }
    }
    public static String[] generateSymbols(int size) {
        String[] symbols = new String[size];
        for (int i = 0; i < size; i++) {
            if (i < 9) {
                symbols[i] = String.valueOf(i + 1);
            } else {
                char symbol = (char) ('A' + i - 9);
                symbols[i] = String.valueOf(symbol);
            }
        }
        return symbols;
    }
    static private int getSymbolValue(String symbol) {


        if (symbol.matches("[0-9]")) {
            return Integer.parseInt(symbol);
        } else {
            char ch = symbol.charAt(0);
            return 10 + (ch - 'A');
        }
    }
    public static String getSymbol(int value){
        if (value == 0) {
            return " ";
        } else if (value < 10) {
            return String.valueOf(value);
        } else {
            char symbol = (char) ('A' + value - 10);
            return String.valueOf(symbol);
        }
    }
}
