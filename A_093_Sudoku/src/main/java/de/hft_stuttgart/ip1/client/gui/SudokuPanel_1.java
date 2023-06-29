package de.hft_stuttgart.ip1.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/* Panel beinhaltet das Sudoku-Raster
 * Panel wird von SudokuFrame aufgerufen*/

public class SudokuPanel_1 extends JPanel {

    private int size;
    private int [] grid;

    public SudokuPanel_1(int[] grid, int size) {
        this.setPreferredSize(new Dimension(800, 500));
        this.grid = grid;
        this.size = size;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(173,216,230)); //White

        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2d.setColor(new Color(0.0f,0.0f,0.0f)); //Black-Lines
        int slotWidth = this.getWidth() / size;
        int slotHeight = this.getHeight() / size;

        //Zeichne Linien in x-Richtung
        for (int x = 0; x <= this.getWidth(); x+= slotWidth) {

            //Zeichne Zahlen
            g2d.setFont(new Font("Arial", Font.BOLD, 23));
            g2d.setColor(Color.blue);
            for (int i = 1; i < size; i++) {
                for (int j = 1; j < size; j++) {
                    int zahl = grid[i];
                    g2d.drawString(Integer.toString(grid[i*j]),getWidth() / (size-i) +10,getHeight() / (size-j) +10);
                }
            }

            if ((x / slotWidth) % (int) Math.sqrt(size) == 0){
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(x,0, x, this.getHeight());
            } else {
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(x,0, x, this.getHeight());
            }
        }

        //Zeichne Linien in y-Richtung
        for (int y = 0; y <= this.getHeight(); y+= slotHeight) {
            if ((y / slotHeight) % (int) Math.sqrt(size) == 0){
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(0, y, this.getWidth(), y);
            } else {
                g2d.setStroke(new BasicStroke(1));
                g2d.drawLine(0, y, this.getWidth(), y);
            }
        }

    }

}
