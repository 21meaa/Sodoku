package de.hft_stuttgart.ip1.client.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;

/** Klasse ermoeglicht es, die Buttons in WelcomeFrame abzurunden */

class RoundedBorder implements Border {

    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    //Methode gibt die Abstaende zwischen Komponente und ihrem Rahmen zurueck.
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    //Opaque gibt an, ob Rahmenlinie einer Komponente undurchsichtig ist (true) oder nicht (false)
    public boolean isBorderOpaque() {
        return true;
    }

    //Methode zeichnet die abgerundete Rahmenlinie um die Komponente
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
