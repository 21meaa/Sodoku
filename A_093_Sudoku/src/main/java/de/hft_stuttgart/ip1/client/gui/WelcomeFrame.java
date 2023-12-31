package de.hft_stuttgart.ip1.client.gui;

import de.hft_stuttgart.ip1.StudentName;
import de.hft_stuttgart.ip1.Students;
import de.hft_stuttgart.ip1.client.Client;
import de.hft_stuttgart.ip1.client.MainFrame;
import de.hft_stuttgart.ip1.common.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class WelcomeFrame extends JFrame {

    private JPanel windowPanel;
    private JLabel labelHeading;
    private JLabel labelQuestion;
    private JButton btn16x16;
    private JButton btn25x25;
    private JButton btn9x9;
    private JComboBox comboBox;
    private JLabel lblWhichDifficulty;
    private int size;
    private double difficulty = 0.4;
    private String name = StudentName.getStudentName();
    private int port = Students.getPort(name);
    private Registry registry = LocateRegistry.getRegistry(port);
    private Session session = (Session) registry.lookup(Session.class.getName());
    private int[] grid;

    //Starte Game -> wird in Client aufgerufen und geoeffnet
    public static void main(String[] args) throws RemoteException, NotBoundException {
        SwingUtilities.invokeLater(() -> {
            WelcomeFrame frame = null;
            try {
                frame = new WelcomeFrame();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            } catch (NotBoundException e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(true);
            frame.setResizable(false); //verhindert größenveränderung des Fensters
            if (frame.isActive() == false) {
                /**
                 * Alle Sessions etc disposen, wenn beide Frames down sind !?
                 * */
            }
        });

    }

    public WelcomeFrame() throws HeadlessException, RemoteException, NotBoundException {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Sudoku Game");
        this.setMinimumSize(new Dimension(490, 250));

        //Definiere das Main-Panel
        windowPanel = new JPanel();
        windowPanel.setBackground(new Color(0x220456));
        windowPanel.setPreferredSize(new Dimension(490, 250));
        this.setContentPane(windowPanel);

        placeLabels();
        placeButtons();

    }

    private void placeLabels() {

        //Label Welcome
        labelHeading = new JLabel("~ Wilkommen bei Sudoku ~");
        labelHeading.setBounds(41, 17, 393, 34);
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        labelHeading.setForeground(Color.white);
        //labelHeading.setBackground(new Color(0x4B19A2));
        labelHeading.setFont(new Font("Tahoma", Font.BOLD, 19));

        //Label Frage
        labelQuestion = new JLabel("Welche Variante des Spiels möchten Sie starten?");
        labelQuestion.setBounds(41, 57, 393, 45);
        labelQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        labelQuestion.setForeground(Color.white);
        labelQuestion.setFont(new Font("Tahoma", Font.PLAIN, 13));
    }

    private void placeButtons() {

        //Button fuer Size 16x16
        btn16x16 = new JButton("16x16");
        btn16x16.setBounds(190, 120, 89, 31);
        btn16x16.setForeground(Color.white);
        btn16x16.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn16x16.setFocusPainted(false);
        btn16x16.setBorder(new RoundedBorder(10));
        btn16x16.setBackground(new Color(0x4B19A2));
        btn16x16.addActionListener(e -> {
            size = 16;
            /**
             * Size und Difficulty bestimmen, addGame irgendwie aufrufen
             * Schwierigeitsgrade: 0.4, 0.6, 0.8,
             * -> wenn Button gedrückt, frage nach Position/Nummer in ComboBox um Schwierigkeitsgrad zu bekommen
             */
            createGame();
            try {
                new MainFrame(session, size, grid).setVisible(true);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        });

        //Button fuer Size 25x25
        btn25x25 = new JButton("25x25");
        btn25x25.setBounds(331, 120, 89, 31);
        btn25x25.setForeground(Color.WHITE);
        btn25x25.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn25x25.setFocusPainted(false);
        btn25x25.setBorder(new RoundedBorder(10));
        btn25x25.setBackground(new Color(75, 25, 162));
        btn25x25.addActionListener(e -> {
            size = 25;
            /**
             * Size und Difficulty bestimmen, addGame irgendwie aufrufen
             * Schwierigeitsgrade: 0.4, 0.6, 0.8,
             * -> wenn Button gedrückt, frage nach Position/Nummer in ComboBox um Schwierigkeitsgrad zu bekommen
             */
            createGame();
            try {
                new MainFrame(session, size, grid).setVisible(true);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        });

        //Button fuer Size 9x9
        btn9x9 = new JButton("9x9");
        btn9x9.setBounds(55, 120, 89, 31);
        btn9x9.setForeground(Color.WHITE);
        btn9x9.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn9x9.setFocusPainted(false);
        btn9x9.setBorder(new RoundedBorder(10));
        btn9x9.setBackground(new Color(75, 25, 162));
        btn9x9.addActionListener(e -> {
            size = 9;
            /**
             * Size und Difficulty bestimmen, addGame irgendwie aufrufen
             * Schwierigeitsgrade: 0.4, 0.6, 0.8,
             * -> wenn Button gedrückt, frage nach Position/Nummer in ComboBox um Schwierigkeitsgrad zu bekommen
             *
             */
            createGame();
            try {
                new MainFrame(session, size, grid).setVisible(true);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        });

        //Label Difficulty
        lblWhichDifficulty = new JLabel("Difficulty: ");
        lblWhichDifficulty.setBounds(134, 169, 89, 22);
        lblWhichDifficulty.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblWhichDifficulty.setForeground(Color.white);

        //ComboBox zur Auswahl der Difficulty
        comboBox = new JComboBox();
        comboBox.setBackground(new Color(0x4B19A2));
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
        comboBox.setForeground(Color.white);
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Easy", "Medium", "Hard"}));
        comboBox.setBounds(233, 171, 101, 21);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedItem().toString().equals("Easy")) {

                    difficulty = 0.4;
                }else if (comboBox.getSelectedItem().toString().equals("Medium")){
                    difficulty = 0.6;
                }else {
                    difficulty = 0.8;
                }
            }
        });

        //Fuegt alle Components in das Main-Panel ein
        windowPanel.setLayout(null); //Ist auf Absolute Layout
        windowPanel.add(labelQuestion);
        windowPanel.add(labelHeading);
        windowPanel.add(btn9x9);
        windowPanel.add(btn16x16);
        windowPanel.add(btn25x25);
        windowPanel.add(lblWhichDifficulty);
        windowPanel.add(comboBox);
    }
    private void createGame(){
        try {
            grid = session.addGame(size, difficulty);
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }
}