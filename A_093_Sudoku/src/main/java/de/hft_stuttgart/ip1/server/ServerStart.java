
package de.hft_stuttgart.ip1.server;

import de.hft_stuttgart.ip1.StudentName;
import de.hft_stuttgart.ip1.Students;
import de.hft_stuttgart.ip1.common.Session;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerStart{

    public int size; //Delete?
    public static void main(String args[]) throws RemoteException{

        String name = StudentName.getStudentName(); //Holt Studenten
        int port = Students.getPort(name); //?
        Registry registry = LocateRegistry.createRegistry(port); //?
        SessionHandler sessionHandler = new SessionHandler(); //Holt alle Soudku-Varialen?
        UnicastRemoteObject.exportObject(sessionHandler, port); //?

        try {
            registry.bind(Session.class.getName(), sessionHandler);
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Server Runs");
    }
}

