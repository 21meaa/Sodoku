
package de.hft_stuttgart.ip1.client;

import de.hft_stuttgart.ip1.StudentName;
import de.hft_stuttgart.ip1.Students;
import de.hft_stuttgart.ip1.common.SchnittstelleServerClient;

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
        SchnittstelleServerClient schnittstelleServerClient = (SchnittstelleServerClient) registry.lookup(SchnittstelleServerClient.class.getName());
        schnittstelleServerClient.generate();
        schnittstelleServerClient.printGrid();
        EventQueue.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}

