package de.hft_stuttgart.ip1.client.gui;

import de.hft_stuttgart.ip1.client.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class SudokuPanel extends JPanel {
    private int gridCount = 10;
    private int widthDivisor = 10;
    private int heightDivisor = 12;
    private Dimension size;
    private int startX = 0;
    private int startY = 0;
    private final JPanel parent;
    private int boxX = -1;
    private int boxY = -1;
    private Character dataField[] = new Character[81];

    public SudokuPanel(JPanel upperPane) {
        this.parent = upperPane;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                    computeSize();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Point point = e.getPoint();
                boxX = boxY = -1;
                outer:
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if ( getX(i)<=point.x && getX(i+1)>point.x &&
                            getY(j)<=point.y && getY(j+1)>point.y ) {
                            boxX = i;
                            boxY = j;
                            break outer;
                        }
                    }
                }
                repaint();
            }
        });
    }
    public void generateSudoku(){
        int squareRoot = (int) Math.sqrt(dataField.length);
        for (int i = 0; i<dataField.length/squareRoot;i++){

        }
    }
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        if ( size == null ) {
            computeSize();
        }
        Graphics2D g2d = (Graphics2D) gc;
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        Rectangle2D rcTitle = g2d.getFontMetrics().getStringBounds("Sudoku", g2d);
        g2d.drawString("Sudoku", (int)(size.width/2-rcTitle.getWidth()/2),
                (int)(5+rcTitle.getHeight()));

        Stroke thinStroke = new BasicStroke(1.0f);
        Stroke thickStroke = new BasicStroke(2.0f);
        for (int i=0; i<10; i++) {
            g2d.setStroke(i%3==0?thickStroke:thinStroke);
            g2d.drawLine(getX(0), getY(i),
                    getX(9), getY(i));
        }
        for (int i=0; i<10; i++) {
            g2d.setStroke(i%3==0?thickStroke:thinStroke);
            g2d.drawLine(getX(i), getY(0),
                    getX(i), getY(9));
        }
        if ( boxX != -1 && boxY != -1 ) {
            Rectangle rcBox = new Rectangle(getX(boxX)+2, getY(boxY)+2,
                    getX(1)-getX(0)-4, getY(1)-getY(0)-4);
            g2d.setColor(Color.YELLOW);
            g2d.fillRect(rcBox.x, rcBox.y, rcBox.width, rcBox.height);
        }

        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if ( dataField[9*y+x] != null) {
                    String data = dataField[9*y+x].toString();
                    Rectangle rcBox = new Rectangle(getX(x)+2, getY(y)+2,
                            getX(1)-getX(0)-4, getY(1)-getY(0)-4);
                    Rectangle2D rcText = g2d.getFontMetrics().getStringBounds( data, g2d);
                    g2d.setColor(Color.BLUE);
                    g2d.drawString(data,
                            rcBox.x+rcBox.width/2-(int)rcText.getWidth()/2,
                            rcBox.y+5*rcBox.height/6);
                }
            }
        }

    }

    public void setData(Character c) {

        if ( boxX != -1 && boxY != -1 ) {
            if (Objects.equals(dataField[9*boxY+boxX], c) ) {
                dataField[9 * boxY + boxX] = null;
            }
            else {
                dataField[9*boxY+boxX] = c;
            }
            repaint();
        }
    }
    private void computeSize() {
        size = getSize();
        startX = size.width/(widthDivisor+2);
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        Rectangle2D rcTitle = g2d.getFontMetrics().getStringBounds("Sudoku", g2d);
        startY = size.height/(heightDivisor-1)+(int)rcTitle.getHeight();
        g2d.dispose();
    }

    private int getX(int i) {
        return startX+i*size.width/11;
    }

    private int getY(int i) {
        return startY+i*size.height/12;
    }
}
