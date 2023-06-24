package de.hft_stuttgart.ip1.server;

import de.hft_stuttgart.ip1.common.GenerateSodoku;

import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.IntStream;

public class Sodoku implements GenerateSodoku {


     Random ran = new Random();
     private int size;
    private final  int BOX_LENGHT;
    private int[][] grid;
    private int[] sym;

    public static void main(String[] args) throws RemoteException {

    }
    @Override
    public void ausgabe(int [][] grid) throws RemoteException{
        for(int i = 0; i < size; i++){
            for (int j = 0; j  < size; j ++){
                if(j == 0){
                    System.out.print("|");
                }
                if(grid[i][j] > 9){
                    System.out.print(getSymbol(grid[i][j]));
                }else {
                    System.out.print(grid[i][j]);
                }

                if((j+1) % Math.sqrt(size) == 0){
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.println("------------------");
        }
    }

    public Sodoku(int size) throws RemoteException {
        this.size = size;
        BOX_LENGHT =  (int)Math.sqrt(size);
        grid = new int[size][size];
        sym  = IntStream.range(1, size+1).toArray();

        shuffleArray(sym);
        fillBlockDiagonal(grid, sym, 0, 0);
        solveSoduku(grid, sym);
    }


    @Override
    public void fillBlockDiagonal(int[][] grid, int[] symbols, int row, int column)throws RemoteException{

        for(int dia = 0; dia < size; dia += BOX_LENGHT){

            int boxRow = row - row % BOX_LENGHT;
            int boxColumn = column - column % BOX_LENGHT;

            for (int i = boxRow; i < boxRow + BOX_LENGHT; i++){
                for (int j = boxColumn; j < boxColumn + BOX_LENGHT; j++){
                    if (grid[i][j] == 0){

                        for(int k = 0 ; k < symbols.length; k++){
                            if(isValid(grid, symbols[k], row, column)){
                                grid[i][j] = symbols[k];
                            }
                        }
                    }
                }
            }
            column += BOX_LENGHT;
            row+= BOX_LENGHT;
        }

    }
    @Override
    public boolean solveSoduku(int[][] grid, int[] symbols)throws RemoteException{

        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(grid[i][j] == 0){
                    for(int k = 0; k < symbols.length; k++){
                        if(isValid(grid, symbols[k], i, j)){
                            grid[i][j] = symbols[k];
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

    @Override
    public void shuffleArray(int[] array) throws RemoteException{
        Random rand = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    @Override
    public String getSymbol(int value)throws RemoteException{
        if (value == 0) {
            return " ";
        } else if (value < 10) {
            return String.valueOf(value);
        } else {
            char symbol = (char) ('A' + value - 10);
            return String.valueOf(symbol);
        }
    }
    @Override
    public boolean isInRow(int[][] grid, int row, int number)throws RemoteException{


        for (int i = 0; i < size; i++ ){
            if(grid[row][i] == number){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isInColumn(int[][] grid, int column, int number)throws RemoteException{

        for (int i = 0; i < size; i++ ){
            if(grid[i][column] == number){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isInBox(int[][] grid, int row, int column, int number)throws RemoteException{


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

    @Override
    public boolean isValid(int[][] grid, int number, int row, int column)throws RemoteException{

        return !isInRow(grid, row, number) && !isInColumn(grid, column, number) && !isInBox(grid, row, column, number);
    }

    public Random getRan() {
        return ran;
    }

    public int getSize() {
        return size;
    }

    public int getBOX_LENGHT() {
        return BOX_LENGHT;
    }

    public int[] getGrid() throws RemoteException{
        int counter = 0;
        int[] ret = new int[size* size];
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                ret[counter]= grid[i][j];
            }
        }
        return ret;
    }

    public int[] getSym() {
        return sym;
    }

}
