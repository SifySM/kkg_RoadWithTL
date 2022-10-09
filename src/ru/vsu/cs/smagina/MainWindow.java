package ru.vsu.cs.smagina;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final DrawPanel panel;

    public MainWindow(int width, int height) throws HeadlessException {
        this.setSize(width, height);
        panel = new DrawPanel(width, height,100);
        this.add(panel);
    }
}