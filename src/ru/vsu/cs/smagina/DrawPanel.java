package ru.vsu.cs.smagina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawPanel extends JPanel implements ActionListener  {
    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private final int TIMER_DELAY;
    private Timer timerBackground;
    private Text text;

    private int ticksFromStart = 0;
    private int i = 0;
    Background background;

    public DrawPanel(final int width, final int height, final int timerDelay) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.TIMER_DELAY = timerDelay;
        timerBackground = new Timer(timerDelay, this);
        timerBackground.start();
        this.background = new Background(PANEL_HEIGHT, PANEL_WIDTH);
        this.text = new Text();
    }


    @Override
    public void paint(final Graphics gr) {
        super.paint(gr);
        background.drawSky(gr, i);

        background.drawRoad(gr);

        text.writeText(gr);
    }

    //входной параметр событие,
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timerBackground) {
            //background.update();
            repaint();
            ++ticksFromStart;

            if(i < 225) {i+=2;}
        }
    }
}
