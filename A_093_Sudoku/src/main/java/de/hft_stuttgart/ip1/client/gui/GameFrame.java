package de.hft_stuttgart.ip1.client.gui;

import de.hft_stuttgart.ip1.common.Session;
import de.hft_stuttgart.ip1.server.Sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JButton;
import de.hft_stuttgart.ip1.client.gui.SudokuPanel;

public class GameFrame extends JFrame {

    private JPanel windowPanel;
    private JMenuBar menuBar;
    private JMenu mnMenu;
    private JMenuItem mn_it_BackToStart;
    private JMenuItem mn_it_Exit;

    private JPanel panelGameField;
    private SudokuPanel_1 sPanel;

    private JPanel panelButtons;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btn10;
    private JButton btn11;
    private JButton btn12;
    private JButton btn13;
    private JButton btn14;
    private JButton btn15;
    private JButton btn16;
    private JButton btn17;
    private JButton btn18;
    private JButton btn19;
    private JButton btn20;
    private JButton btn21;
    private JButton btn22;
    private JButton btn23;
    private JButton btn24;
    private JButton btn25;
    private int[] grid;
    private Session session;
    private int size;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GameFrame frame = new GameFrame(null, null, 0);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GameFrame(int [] grid, Session session, int size) throws HeadlessException, RemoteException{
        this.size = size;
        this.grid = grid;
        this.session = session;
        this.setResizable(false); //verhindert groeßenveraenderung des Fensters
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sudoku Game");
        this.setMinimumSize(new Dimension(900, 700));

        //Main Panel
        windowPanel = new JPanel();
        windowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        windowPanel.setBackground(new Color(0x220456));
        windowPanel.setPreferredSize(new Dimension(490, 220));
        this.setContentPane(windowPanel);

        placeNavbar();
        placeButtons();
        placeGameField();
        defineLayout();

    }

    private void placeNavbar() {

        //Defintion v. Menu-Balken
        menuBar = new JMenuBar();
        menuBar.setSize(new Dimension(50, 100));
        this.setJMenuBar(menuBar);

        //Setzte ein Menu mit Items: BackToWelcome, Exit
        mnMenu = new JMenu("Main-Menu");
        mnMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
        menuBar.add(mnMenu);

        mn_it_BackToStart = new JMenuItem("Back to Start");
        //Abfrage per JOptionPane, ob wirklich zurück zum WelcomeFrame; Yes == 0, No == 1, X = -1
        mn_it_BackToStart.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(mn_it_BackToStart, "Do you want to go back to the Main-Menu and choose a different game?", "Go back to WelcomeFrame", JOptionPane.YES_NO_OPTION) == 0) {
                try {
                    new WelcomeFrame().setVisible(true);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                } catch (NotBoundException ex) {
                    throw new RuntimeException(ex);
                }
                this.dispose();
                System.out.println("Go back to welcomeFrame");
            } else {
                System.out.println("Return to Game");
            }
        });
        mnMenu.add(mn_it_BackToStart);

        mn_it_Exit = new JMenuItem("Exit");
        //Abfrage per JOptionPane, ob wirklich Spiel beenden
        mn_it_Exit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(mn_it_Exit, "Are you sure, that you want to leave the game?", "Exit the game?", JOptionPane.YES_NO_OPTION) == 0) {
                JOptionPane.showMessageDialog(null, "Thank you for playing!");
                this.dispose();
                System.out.println("Exit the Game");
            } else {
                System.out.println("Return to Game");
            }
        });
        mnMenu.add(mn_it_Exit);
    }

    private void placeButtons() {
        //Extra Panel mit allen Buttons drin
        panelButtons = new JPanel();
        panelButtons.setBounds(10, 555, 866, 76);
        panelButtons.setBackground(new Color(0x220456));
        panelButtons.setLayout(null); //Absolut Layout

        //Alle Buttons zur Wahl von Zahl/Buchstabe
        btn1 = new JButton("1");
        btn1.setBounds(30, 5, 85, 21);
        setButtonProperties(btn1); //siehe Methode unten
        panelButtons.add(btn1);

        /**
         * Actions für alle Buttons einfügen
         * Hiermiet?:
         *      a1Button.setAction(new ButtonAction('1'));
         *
         *      private class ButtonAction extends AbstractAction {
         *         private final Character c;
         *
         *         public ButtonAction(Character c) {
         *             this.c = c;
         *             this.putValue(Action.NAME, c.toString());
         *         }
         *
         *         @Override
         *         public void actionPerformed(ActionEvent actionEvent) {
         *             ((SudokuPanel) sudokuPanel).setData(c);
         *         }
         *     }
         */

        btn2 = new JButton("2");
        btn2.setBounds(120, 5, 85, 21);
        setButtonProperties(btn2);
        panelButtons.add(btn2);

        btn3 = new JButton("3");
        btn3.setBounds(210, 5, 85, 21);
        setButtonProperties(btn3);
        panelButtons.add(btn3);

        btn4 = new JButton("4");
        btn4.setBounds(300, 5, 85, 21);
        setButtonProperties(btn4);
        panelButtons.add(btn4);

        btn5 = new JButton("5");
        btn5.setBounds(390, 5, 85, 21);
        setButtonProperties(btn5);
        panelButtons.add(btn5);

        btn6 = new JButton("6");
        btn6.setBounds(480, 5, 85, 21);
        setButtonProperties(btn6);
        panelButtons.add(btn6);

        btn7 = new JButton("7");
        btn7.setBounds(570, 5, 85, 21);
        setButtonProperties(btn7);
        panelButtons.add(btn7);

        btn8 = new JButton("8");
        btn8.setBounds(660, 5, 85, 21);
        setButtonProperties(btn8);
        panelButtons.add(btn8);

        btn9 = new JButton("9");
        btn9.setBounds(750, 5, 85, 21);
        setButtonProperties(btn9);
        panelButtons.add(btn9);

        btn10 = new JButton("A");
        btn10.setBounds(120, 30, 85, 21);
        setButtonProperties(btn10);
        panelButtons.add(btn10);

        btn11 = new JButton("B");
        btn11.setBounds(210, 30, 85, 21);
        setButtonProperties(btn11);
        panelButtons.add(btn11);

        btn12 = new JButton("C");
        btn12.setBounds(300, 30, 85, 21);
        setButtonProperties(btn12);
        panelButtons.add(btn12);

        btn13 = new JButton("D");
        btn13.setBounds(390, 30, 85, 21);
        setButtonProperties(btn13);
        panelButtons.add(btn13);

        btn14 = new JButton("E");
        btn14.setBounds(480, 30, 85, 21);
        setButtonProperties(btn14);
        panelButtons.add(btn14);

        btn15 = new JButton("F");
        btn15.setBounds(570, 30, 85, 21);
        setButtonProperties(btn15);
        panelButtons.add(btn15);

        btn16 = new JButton("G");
        btn16.setBounds(660, 30, 85, 21);
        setButtonProperties(btn16);
        panelButtons.add(btn16);

        btn17 = new JButton("H");
        btn17.setBounds(30, 55, 85, 21);
        setButtonProperties(btn17);
        panelButtons.add(btn17);

        btn18 = new JButton("I");
        btn18.setBounds(120, 55, 85, 21);
        setButtonProperties(btn18);
        panelButtons.add(btn18);

        btn19 = new JButton("J");
        btn19.setBounds(210, 55, 85, 21);
        setButtonProperties(btn19);
        panelButtons.add(btn19);

        btn20 = new JButton("K");
        btn20.setBounds(300, 55, 85, 21);
        setButtonProperties(btn20);
        panelButtons.add(btn20);

        btn21 = new JButton("L");
        btn21.setBounds(390, 55, 85, 21);
        setButtonProperties(btn21);
        panelButtons.add(btn21);

        btn22 = new JButton("M");
        btn22.setBounds(480, 55, 85, 21);
        setButtonProperties(btn22);
        panelButtons.add(btn22);

        btn23 = new JButton("N");
        btn23.setBounds(570, 55, 85, 21);
        setButtonProperties(btn23);
        panelButtons.add(btn23);

        btn24 = new JButton("O");
        btn24.setBounds(660, 55, 85, 21);
        setButtonProperties(btn24);
        panelButtons.add(btn24);

        btn25 = new JButton("P");
        btn25.setBounds(750, 55, 85, 21);
        setButtonProperties(btn25);
        panelButtons.add(btn25);
    }

    //Gib allen Zahlen/Buchstaben-Buttons die gleichen Eigenschaften
    private void setButtonProperties(JButton btn) {
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btn.setFocusPainted(false); //Entferne Rahmen um den Text innerhalb des Buttons
        btn.setBorder(new RoundedBorder(10)); //Roundedborder = eigene Klasse
        btn.setBackground(new Color(75, 25, 162));
    }

    //Spielfeld mit Sudoku-Feld drinnen
    private void placeGameField() {
        panelGameField = new JPanel();
        panelGameField.setBounds(31, 23, 825, 510);
        panelGameField.setBackground(new Color(0x220456));

        //Setzte Klasse SudokuPanel als windowPanel
        sPanel = new SudokuPanel_1(grid, size);
        panelGameField.add(sPanel);
        windowPanel.add(panelGameField);

        /**
         * Sudoku-Fela hier einfügen
         * Verbinden: in Panel session.addGame(size, difficulty);
         * -> bekomme size und difficulty von ButtonAuswahl in WelcomeFrame, aber wie?
         */

    }

    private void defineLayout() {
        windowPanel.setLayout(null); //Absolut Layout
        windowPanel.add(panelButtons);
        windowPanel.add(panelGameField);
    }
}
