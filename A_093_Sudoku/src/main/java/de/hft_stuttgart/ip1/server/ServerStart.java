
package de.hft_stuttgart.ip1.server;

import de.hft_stuttgart.ip1.StudentName;
import de.hft_stuttgart.ip1.Students;
import de.hft_stuttgart.ip1.common.SchnittstelleServerClient;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerStart {
    public static void main(String args[]) throws RemoteException{
        String name = StudentName.getStudentName();
        int port = Students.getPort(name);
        Registry registry = LocateRegistry.createRegistry(port);
        SudokuSpiel game = new SudokuSpiel(9);

        UnicastRemoteObject.exportObject(game, port);
        try {
            registry.bind(SchnittstelleServerClient.class.getName(), game);
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Server Runs");
    }
}

