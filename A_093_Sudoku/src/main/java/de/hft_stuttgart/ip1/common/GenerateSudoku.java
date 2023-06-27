package de.hft_stuttgart.ip1.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GenerateSudoku extends Remote {


    void fillBlockDiagonal(int[][] grid, int[] symbols, int row, int column) throws RemoteException;

    boolean solveSoduku(int[][] grid, int[] symbols) throws RemoteException;

    void shuffleArray(int[] array)throws RemoteException;

    String getSymbol(int value)throws RemoteException;

    boolean isInRow(int[][] grid, int row, int number)throws RemoteException;

    boolean isInColumn(int[][] grid, int column, int number)throws RemoteException;

    boolean isInBox(int[][] grid, int row, int column, int number)throws RemoteException;

    boolean isValid(int[][] grid, int number, int row, int column)throws RemoteException;

    void ausgabe(int[][] grid) throws RemoteException;
    int[] getGrid() throws RemoteException;
    void removeCells() throws RemoteException;
    boolean solutions(int[] symbols, int[][] grid) throws RemoteException;


}
