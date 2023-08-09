package de.hft_stuttgart.ip1.client.actions;

import de.hft_stuttgart.ip1.client.MainFrame;
import de.hft_stuttgart.ip1.common.Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class ExitAction extends AbstractAction {
    private final MainFrame mainFrame;
    private Session session;

    public ExitAction(MainFrame mainFrame, Session session) throws RemoteException {
        super("Beenden");
        this.mainFrame = mainFrame;
        this.session = session;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(mainFrame, "Exit");
        mainFrame.setVisible(false);
        try {
            session.cleanGames();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        mainFrame.dispose();
        System.exit(0);
    }
}
