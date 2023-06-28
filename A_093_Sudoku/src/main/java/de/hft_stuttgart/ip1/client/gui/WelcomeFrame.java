package de.hft_stuttgart.ip1.client.gui;

import de.hft_stuttgart.ip1.common.Session;

import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class WelcomeFrame extends JFrame {

    private JPanel windowPanel;
    private JLabel labelHeading;
    private JLabel labelQuestion;
    private JButton btn16x16;
    private JButton btn25x25;
    private JButton btn9x9;

    private static Session session;

    /*
     * Starte Game -> wird in Client aufgerufen und geöffnet
     * Main aktuell nur zum testen, später wegmachen
     * COmboBox für Schwierigkeit einbauen, für alle drei größen
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomeFrame frame = new WelcomeFrame();
            frame.setVisible(true);
            frame.setResizable(false); //verhindert größenveränderung des Fensters
        });
    }

    public WelcomeFrame() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sudoku Game");
        this.setMinimumSize(new Dimension(490, 220));

        //Define Panel in Frame
        windowPanel = new JPanel();
        windowPanel.setBackground(new Color(0x220456));
        windowPanel.setPreferredSize(new Dimension(490, 220));
        getContentPane().add(windowPanel);

        placeLabels();
        placeButtons();
        defineLayout();
    }

    private void placeLabels() {
        //Label Welcome
        labelHeading = new JLabel("~ Wilkommen bei Sudoku ~");
        labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
        labelHeading.setForeground(Color.white);
        //labelHeading.setBackground(new Color(0x4B19A2));
        labelHeading.setFont(new Font("Tahoma", Font.BOLD, 19));

        //Label Frage
        labelQuestion = new JLabel("Welche Variante des Spiels möchten Sie starten?");
        labelQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        labelQuestion.setForeground(Color.white);
        labelQuestion.setFont(new Font("Tahoma", Font.PLAIN, 13));
    }

    private void placeButtons() {
        //Button 16x16
        btn16x16 = new JButton("16x16");
        btn16x16.setForeground(Color.white);
        btn16x16.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn16x16.setFocusPainted(false);
        btn16x16.setBorder(new RoundedBorder(10));
        btn16x16.setBackground(new Color(0x4B19A2));
        btn16x16.addActionListener(e -> {

        });

        //Button 25x25
        btn25x25 = new JButton("25x25");
        btn25x25.setForeground(Color.WHITE);
        btn25x25.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn25x25.setFocusPainted(false);
        btn25x25.setBorder(new RoundedBorder(10));
        btn25x25.setBackground(new Color(75, 25, 162));
        btn25x25.addActionListener(e -> {

        });

        //Button 9x9
        btn9x9 = new JButton("9x9");
        btn9x9.setForeground(Color.WHITE);
        btn9x9.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btn9x9.setFocusPainted(false);
        btn9x9.setBorder(new RoundedBorder(10));
        btn9x9.setBackground(new Color(75, 25, 162));
        btn9x9.addActionListener(e -> {

        });

//        btn9x9.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                wantedType = SudokuPuzzleType.NINEBYNINE;
//                System.out.println("9x9");
//                new SudokuFrame().setVisible(true);
//            }
//        });

    }

    private void defineLayout() {

        GroupLayout gl_windowPanel = new GroupLayout(windowPanel);
        gl_windowPanel.setHorizontalGroup(
                gl_windowPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_windowPanel.createSequentialGroup()
                                .addGroup(gl_windowPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_windowPanel.createSequentialGroup()
                                                .addGap(41)
                                                .addGroup(gl_windowPanel.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(labelQuestion, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(labelHeading, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_windowPanel.createSequentialGroup()
                                                .addGap(55)
                                                .addComponent(btn9x9, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                                .addGap(46)
                                                .addComponent(btn16x16, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                                .addGap(52)
                                                .addComponent(btn25x25, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(42, Short.MAX_VALUE))
        );
        gl_windowPanel.setVerticalGroup(
                gl_windowPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_windowPanel.createSequentialGroup()
                                .addGap(17)
                                .addComponent(labelHeading, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(labelQuestion, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addGroup(gl_windowPanel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btn16x16, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn25x25, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn9x9, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(62))
        );
        windowPanel.setLayout(gl_windowPanel);
    }
}
