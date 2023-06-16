package de.hft_stuttgart.ip1.client.actions;

import de.hft_stuttgart.ip1.client.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LogoutAction extends AbstractAction {
    private final MainFrame mainFrame;

    public LogoutAction(MainFrame mainFrame) {
        super("Ausloggen");
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(mainFrame, "Logout");
    }
}
