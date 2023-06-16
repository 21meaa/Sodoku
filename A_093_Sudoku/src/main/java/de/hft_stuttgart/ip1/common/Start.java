package de.hft_stuttgart.ip1.common;

import de.hft_stuttgart.ip1.client.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Start extends Remote {
    void start() throws RemoteException;

}
