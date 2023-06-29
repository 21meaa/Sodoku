
package de.hft_stuttgart.ip1.client;

import de.hft_stuttgart.ip1.StudentName;
import de.hft_stuttgart.ip1.Students;
import de.hft_stuttgart.ip1.client.gui.WelcomeFrame;
import de.hft_stuttgart.ip1.common.Session;

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
        Session session = (Session) registry.lookup(Session.class.getName());

        System.out.println(session.getGames());

        EventQueue.invokeLater(() -> {
            WelcomeFrame welcomeFrame = null;
            try {
                welcomeFrame = new WelcomeFrame();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            } catch (NotBoundException e) {
                throw new RuntimeException(e);
            }
            welcomeFrame.setVisible(true);

        });

        /*
        EventQueue.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });

         */
    }
}

