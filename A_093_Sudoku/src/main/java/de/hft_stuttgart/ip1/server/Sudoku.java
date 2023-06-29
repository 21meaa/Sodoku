package de.hft_stuttgart.ip1.server;

import de.hft_stuttgart.ip1.common.GenerateSudoku;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.IntStream;

public class Sudoku implements GenerateSudoku {

    Random ran = new Random();
    private int size;
    private final int BOX_LENGTH;
    private int[][] grid;
    private int[][] solution;
    private int[] sym;
    private boolean solutions = false;
    private double difficulty;


    @Override
    public void ausgabe(int[][] grid) throws RemoteException {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j == 0) {
                    System.out.print("|");
                }
                if (grid[i][j] > 9) {
                    System.out.print(getSymbol(grid[i][j]));
                } else {
                    System.out.print(grid[i][j]);
                }

                if ((j + 1) % Math.sqrt(size) == 0) {
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.println("------------------");
        }
    }

    public Sudoku(int size, double difficulty) throws RemoteException {

        this.size = size;
        BOX_LENGTH = (int) Math.sqrt(size);
        grid = new int[size][size];
        sym = IntStream.range(1, size + 1).toArray();
        solution = new int[size][size];
        this.difficulty = difficulty;

        shuffleArray(sym);
        fillBlockDiagonal(grid, sym, 0, 0);
        solveSoduku(grid, sym);
        copySolution();
        removeCells();
    }

    @Override
    public void fillBlockDiagonal(int[][] grid, int[] symbols, int row, int column) throws RemoteException {

        for (int dia = 0; dia < size; dia += BOX_LENGTH) {
            int boxRow = row - row % BOX_LENGTH;
            int boxColumn = column - column % BOX_LENGTH;

            for (int i = boxRow; i < boxRow + BOX_LENGTH; i++) {
                for (int j = boxColumn; j < boxColumn + BOX_LENGTH; j++) {
                    if (grid[i][j] == 0) {

                        for (int k = 0; k < symbols.length; k++) {
                            if (isValid(grid, symbols[k], row, column)) {
                                grid[i][j] = symbols[k];
                            }
                        }
                    }
                }
            }
            column += BOX_LENGTH;
            row += BOX_LENGTH;
        }
    }

    @Override
    public boolean solveSoduku(int[][] grid, int[] symbols) throws RemoteException {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == 0) {
                    for (int k = 0; k < symbols.length; k++) {
                        if (isValid(grid, symbols[k], i, j)) {
                            grid[i][j] = symbols[k];
                            if (solveSoduku(grid, symbols)) {
                                return true;
                            } else {
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
    public void shuffleArray(int[] array) throws RemoteException {

        Random rand = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    @Override
    public String getSymbol(int value) throws RemoteException {

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
    public boolean isInRow(int[][] grid, int row, int number) throws RemoteException {

        for (int i = 0; i < size; i++) {
            if (grid[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInColumn(int[][] grid, int column, int number) throws RemoteException {

        for (int i = 0; i < size; i++) {
            if (grid[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInBox(int[][] grid, int row, int column, int number) throws RemoteException {

        int boxRow = row - row % BOX_LENGTH;
        int boxColumn = column - column % BOX_LENGTH;

        for (int i = boxRow; i < boxRow + BOX_LENGTH; i++) {
            for (int j = boxColumn; j < boxColumn + BOX_LENGTH; j++) {
                if (grid[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isValid(int[][] grid, int number, int row, int column) throws RemoteException {
        return !isInRow(grid, row, number) && !isInColumn(grid, column, number) && !isInBox(grid, row, column, number);
    }

    /**
     * Noch nicht löschen
     */
    public Random getRan() {
        return ran;
    }

    public int getSize() {
        return size;
    }

    public int getBOX_LENGTH() {
        return BOX_LENGTH;
    }

    public int[] getGrid() throws RemoteException {

        int counter = 0;
        int[] ret = new int[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ret[counter] = grid[i][j];
                counter++;
            }
        }
        return ret;
    }
    public int[] getSol() throws RemoteException {

        int counter = 0;
        int[] ret = new int[size * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ret[counter] = solution[i][j];
                counter++;
            }
        }
        return ret;
    }

    @Override
    public void removeCells() throws RemoteException {

        double fractionToRemove = difficulty;
        // Bruchteil der Zellen, die entfernt werden sollen (Zahlen die entfernt werden)

        int buffer;
        for (int i = 0; i < size * size; i++) {
            int row = i / size;
            int col = i % size;

            if (ran.nextDouble() <= fractionToRemove) {
                buffer = grid[row][col];
                grid[row][col] = 0;

                if (solutions(sym, grid)) {
                    grid[row][col] = buffer;
                    solutions = false;
                }
            }
        }
    }

    @Override
    public boolean solutions(int[] symbols, int[][] grid) throws RemoteException {

        int[][] g = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                g[i][j] = grid[i][j];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (g[i][j] == 0) {
                    for (int k = 0; k < symbols.length; k++) {
                        if (isValid(g, symbols[k], i, j)) {
                            g[i][j] = symbols[k];
                            if (solutions(symbols, g)) {
                                return true;
                            } else {
                                g[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        if (solutions) {
            return true;
        }
        solutions = true;
        return false;
    }

    /**
     * noch nicht löschen
     */
    public int[] getSym() {
        return sym;
    }

    private void copySolution() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                solution[i][j] = grid[i][j];
            }
        }
    }

    @Override
    public String toString() {
        return Double.toString(difficulty) + "  " + size;
    }
}
