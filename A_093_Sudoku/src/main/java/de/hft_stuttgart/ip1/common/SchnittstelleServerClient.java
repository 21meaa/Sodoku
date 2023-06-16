package de.hft_stuttgart.ip1.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SchnittstelleServerClient extends Remote {
    String[] generateSymbols(int size)  throws RemoteException;
    void generate()  throws RemoteException;
    void shuffleArray(String[] array)  throws RemoteException;
    boolean isValid(int row, int col, int num)  throws RemoteException;
    void removeCells()  throws RemoteException;
    String getSymbol(int value)  throws RemoteException;
    void fillDiagonalBlocks()  throws RemoteException;
    void fillBlock(int row, int col)  throws RemoteException;
    boolean solve()  throws RemoteException;
    void printGrid() throws RemoteException;


}
