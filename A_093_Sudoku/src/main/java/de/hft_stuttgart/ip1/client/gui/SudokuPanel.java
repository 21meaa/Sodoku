
package de.hft_stuttgart.ip1.client.gui;

import de.hft_stuttgart.ip1.common.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.rmi.RemoteException;
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
    private int dataField[];
    private int sudSize;
    private Session session;

    public SudokuPanel(JPanel upperPane, int sudSize, Session session, int[] grid) {
        this.parent = upperPane;
        this.sudSize = sudSize;
        this.session = session;
        dataField = grid;
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
                for (int i = 0; i < sudSize; i++) {
                    for (int j = 0; j < sudSize; j++) {
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
        for (int i=0; i< sudSize + 1; i++) {
            g2d.setStroke(i % Math.sqrt(sudSize) == 0?thickStroke:thinStroke);
            g2d.drawLine(getX(0), getY(i),
                    getX(sudSize), getY(i));
        }
        for (int i=0; i< sudSize + 1; i++) {
            g2d.setStroke(i % Math.sqrt(sudSize) == 0?thickStroke:thinStroke);
            g2d.drawLine(getX(i), getY(0),
                    getX(i), getY(sudSize));
        }
        if ( boxX != -1 && boxY != -1 ) {
            Rectangle rcBox = new Rectangle(getX(boxX)+2, getY(boxY)+2,
                    getX(1)-getX(0)-4, getY(1)-getY(0)-4);
            g2d.setColor(Color.YELLOW);
            g2d.fillRect(rcBox.x, rcBox.y, rcBox.width, rcBox.height);
        }

        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        for (int y = 0; y < sudSize; y++) {
            for (int x = 0; x < sudSize; x++) {
                if ( dataField[sudSize*y+x] != 0) {
                    String data = Integer.toString(dataField[sudSize * y + x]) ;
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

    public void setData(int c) throws RemoteException {
        if ( boxX != -1 && boxY != -1 ) {
            if (Objects.equals(dataField[ sudSize * boxY+boxX], c) ) {
                dataField[sudSize * boxY + boxX] = 0;

            }
            else {
                dataField[ sudSize * boxY+boxX] = c;
                if (!session.isSolution(dataField)){
                    dataField[ sudSize * boxY+boxX] = 0;
                }


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
        if (dataField.length == 81){
            return startX+i*size.width/11;
        } else if (dataField.length==256){
            return startX+i*size.width/20;
        } else {
            return startX+i*size.width/30;
        }

    }

    private int getY(int i) {
        if (dataField.length == 81){
            return startX+i*size.width/12;
        } else if (dataField.length == 256){
            return startX+i*size.width/26;
        } else {
            return startX+i*size.width/30;
        }
    }
    private String getSymbol(int value) {

        if (value == 0) {
            return " ";
        } else if (value < 10) {
            return String.valueOf(value);
        } else {
            char symbol = (char) ('A' + value - 10);
            return String.valueOf(symbol);
        }
    }

}
