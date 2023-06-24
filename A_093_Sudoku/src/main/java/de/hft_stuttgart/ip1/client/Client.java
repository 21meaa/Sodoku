
package de.hft_stuttgart.ip1.client;

import de.hft_stuttgart.ip1.StudentName;
import de.hft_stuttgart.ip1.Students;
import de.hft_stuttgart.ip1.common.GenerateSodoku;

import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String args[]) throws RemoteException, NotBoundException {
        String name = StudentName.getStudentName();
        int port = Students.getPort(name);
        Registry registry = LocateRegistry.getRegistry(port);
        GenerateSodoku generateSodoku = (GenerateSodoku) registry.lookup(GenerateSodoku.class.getName());

        EventQueue.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}

