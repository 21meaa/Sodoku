package de.hft_stuttgart.ip1.server;

import java.rmi.RemoteException;
import java.util.*;

public class newGame {


    static Random ran = new Random();
    static int size = 25;
    final static int BOX_LENGHT = (int)Math.sqrt(size);

    public static void main(String[] args) {


        int[][] grid = new int[size][size];
        String[] symbols = generateSymbols(size);
        shuffleArray(symbols);
        fillDiagonal(grid, symbols);
        shuffleArray(symbols);
        fillBlockDiagonal(grid, symbols, 0, 0);
        //fillBlock(grid, symbols, BOX_LENGHT, BOX_LENGHT);
        solveSoduku(grid, symbols);





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
        for(String a: symbols){
            System.out.print( "| "+ a + " |");
        }
    }

    public static void fillBlockDiagonal(int[][] grid, String[] symbols, int row, int column){

        for(int dia = 0; dia < size; dia += BOX_LENGHT){

            int boxRow = row - row % BOX_LENGHT;
            int boxColumn = column - column % BOX_LENGHT;

            for (int i = boxRow; i < boxRow + BOX_LENGHT; i++){
                for (int j = boxColumn; j < boxColumn + BOX_LENGHT; j++){
                    if (grid[i][j] == 0){

                        for(int k = 0 ; k < symbols.length; k++){
                            if(isValid(grid, getSymbolValue(symbols[k]), row, column)){
                                grid[i][j] = getSymbolValue(symbols[k]);
                            }
                        }
                    }
                }
            }
            column += BOX_LENGHT;
            row+= BOX_LENGHT;
        }

    }
    public static void fillBlock(int[][] grid, String[] symbols, int row, int column){


        int boxRow = row - row % BOX_LENGHT;
        int boxColumn = column - column % BOX_LENGHT;

        for (int i = boxRow; i < boxRow + BOX_LENGHT; i++){
            for (int j = boxColumn; j < boxColumn + BOX_LENGHT; j++){
                if (grid[i][j] == 0){

                    for(int k = 0 ; k < symbols.length; k++){
                        if(isValid(grid, getSymbolValue(symbols[k]), row, column)){
                            grid[i][j] = getSymbolValue(symbols[k]);
                        }
                    }
                }
            }
        }
    }
    public static boolean solveSoduku(int[][] grid, String[] symbols){

        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(grid[i][j] == 0){
                    for(int k = 0; k < symbols.length; k++){
                        if(isValid(grid, getSymbolValue(symbols[k]), i, j)){
                            grid[i][j] = getSymbolValue(symbols[k]);
                            if(solveSoduku(grid, symbols)){
                                return true;
                            }else {
                                grid[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public static void fillDiagonal(int[][] grid, String[] symbols){

        int counter = 0;
        for(int i = 0; i < grid.length; i++){

            grid[i][i] = getSymbolValue(symbols[counter]);
            counter++;
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
    public static void shuffleArray(String[] array) {
        Random rand = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            String temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
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
    public static boolean isInRow(int[][] grid,int row, int number){


        for (int i = 0; i < size; i++ ){
            if(grid[row][i] == number){
                return true;
            }
        }
        return false;
    }
    public static boolean isInColumn(int[][] grid,int column, int number){

        for (int i = 0; i < size; i++ ){
            if(grid[i][column] == number){
                return true;
            }
        }
        return false;
    }
    public static boolean isInBox(int[][] grid,int row,int column, int number){


        int boxRow = row - row % BOX_LENGHT;
        int boxColumn = column - column % BOX_LENGHT;

        for (int i = boxRow; i < boxRow + BOX_LENGHT; i++){
            for (int j = boxColumn; j < boxColumn + BOX_LENGHT; j++){
                if (grid[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValid(int[][] grid, int number, int row, int column){

        return !isInRow(grid, row, number) && !isInColumn(grid, column, number) && !isInBox(grid, row, column, number);
    }
}
