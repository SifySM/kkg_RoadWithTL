package ru.vsu.cs.smagina;

import java.awt.*;

public class Text {
    public void writeText(Graphics g) {
        Font font = new Font("Broadway",Font.CENTER_BASELINE,70);
        g.setFont(font);
        g.setColor(Color.orange);
        g.drawString("Formula 1", 200, 100);

    }
}
