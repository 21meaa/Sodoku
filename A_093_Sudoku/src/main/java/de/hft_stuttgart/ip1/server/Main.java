
package de.hft_stuttgart.ip1.server;

import de.hft_stuttgart.ip1.StudentName;
import de.hft_stuttgart.ip1.Students;
import de.hft_stuttgart.ip1.common.Start;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {
    public static void main(String args[]) throws RemoteException, AlreadyBoundException {

        String name = StudentName.getStudentName();
        int port = Students.getPort(name);
        Registry registry = LocateRegistry.createRegistry(port);
        StartServer startServer = new StartServer();
        UnicastRemoteObject.exportObject(startServer, 0);
        registry.bind(Start.class.getName(), startServer);
//        System.out.println(port);
//        System.out.println(StudentName.getStudentName());
//        System.out.println("Hello World!");
        startServer.start();
    }
}

//Muss gestartet werden before die client/Main gestartet wird