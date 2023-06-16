package de.hft_stuttgart.ip1.client.actions;

import de.hft_stuttgart.ip1.client.MainFrame;
import de.hft_stuttgart.ip1.client.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginAction extends AbstractAction {
    private final MainFrame mainFrame;

    public LoginAction(MainFrame mainFrame) {
        super("Einloggen");
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JOptionPane.showMessageDialog(mainFrame, "Login");
    }
}
