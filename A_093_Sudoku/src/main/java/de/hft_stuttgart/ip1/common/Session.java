package de.hft_stuttgart.ip1.common;

import de.hft_stuttgart.ip1.server.Sudoku;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Session extends Remote {


    public int[] addGame(int size, double difficulty) throws RemoteException;

    public String getGames() throws RemoteException;

    public boolean isSolution(int[] grid) throws RemoteException;
    public void cleanGames() throws RemoteException;
}
