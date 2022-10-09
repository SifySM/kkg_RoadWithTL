package ru.vsu.cs.smagina;

import java.awt.*;

import static java.lang.Math.PI;
import static java.lang.Math.atan;

public class Background {

    RoadLane roadLanes;
    private int height;
    private double width;
    private int viewingHeight = 7;
    private double wRoad = 6;
    Polygon road;
    TrafficLight trafficLight;
    int[] arrayX;

    public Background(int height, int width) {
        this.height = height;
        this.width = width;


        arrayX = new int[802];
        int[] arrayY = new int[802];

        int y = 0;
        for (int i = 0; i < 802; i++) {
            if (i < 401) y = i;
            else {
                if (i > 401) y--;
            }

            arrayY[i] = 800 - y;

            double lm = (Math.tan(y * PI / height)) * viewingHeight; //перевод из пикселя в метры

            if (i < 401) arrayX[i] = (int) (width / 2 - paramPicture.getX(lm));
            else arrayX[i] = (int) (width / 2 + paramPicture.getX(lm));
        }

        road = new Polygon(arrayX, arrayY, 802);

        this.roadLanes = new RoadLane(width, height, viewingHeight, 2, 100, 50);
        trafficLight = new TrafficLight(60);
    }

    public void drawRoad(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(89, 143, 222));

        g2d.setColor(new Color(57, 180, 46));
        g2d.drawRect(0, 500, 800, 400);
        g2d.fillRect(0, 400, 800, 400);

        g2d.setColor(new Color(41, 41, 41));

        g2d.drawPolygon(road);
        g2d.setColor(new Color(127, 127, 127));
        g2d.fillPolygon(road);

        roadLanes.drawLane(g, 100);
        trafficLight.draw(g, 100, arrayX);
    }

    public void drawSky(Graphics g2d, int i) {
        if (i > 110) {
            i = 0;
        }
        g2d.setColor(new Color(109 - i, 177 - i, 255));
        g2d.fillRect(0, 0, (int) width, height);
    }
}
