package ru.vsu.cs.smagina;

import java.awt.*;

import static java.lang.Math.*;

public class RoadLane {
    private double AX;
    private double AY;
    private double BX;
    private double BY;
    private double CX;
    private double CY;
    private double DX;
    private double DY;
    private double kLeft;
    private double kRight;
    private double bLeft;
    private double bRight;

    private double velocity = 60;
    private int heightPerson;
    private int l0;
    private int dt;
    private int time;
    private int period;
    private int height;
    private int width;

    public RoadLane(int width, int height, int heightPerson, int l, int dt, double velocity) {
        this.height = height;
        this.width = width;

        BX = width / 2 - 1;
        CX = width / 2 - 1;
        AX = BX - 0.01625 * width;
        DX = CX + 0.01625 * width;

        AY = DY = height;
        BY = CY = height / 2;

        this.kLeft = (AY - BY) / (AX - BX);
        this.kRight = -kLeft;
        this.bLeft = (-kLeft * BX + BY);
        this.bRight = (-kRight * CX + CY);

        this.heightPerson = heightPerson;
        l0 = l;
        this.dt = dt;
        setVelocity(velocity);
    }

    private void setVelocity(double velocity) {
        period = (int) (2 * l0 * 3600 / velocity);
        this.velocity = velocity;
    }

    public void drawLane(Graphics g, int dt) {
        Graphics2D g2d = (Graphics2D) g;
        double l = (l0 - velocity * time / 1000); //не пиксили метры
        double endLane =  paramPicture.getY(l + l0); //пиксили
        double startLane = paramPicture.getY(l);
        double dy = endLane - startLane;

        if (startLane < 0) startLane = 0;

        while ((dy >= 1)) {
            AY = DY = height - startLane;
            BY = CY = height - endLane;

            AX = (AY - bLeft) / kLeft;
            BX = (BY - bLeft) / kLeft;
            CX = (CY - bRight) / kRight;
            DX = (DY - bRight) / kRight;

            int[] X = {(int) AX, (int) BX, (int) CX, (int) DX};
            int[] Y = {(int) AY, (int) BY, (int) CY, (int) DY};

            g2d.setColor(Color.white);

            Polygon lane = new Polygon(X, Y, 4);
            g2d.drawPolygon(lane);
            g2d.fillPolygon(lane);

            l += 2*l0;

            endLane = paramPicture.getY(l + l0); //пиксили
            startLane = paramPicture.getY(l);
            dy = endLane - startLane;
        }

        BY = CY = height / 2;
        BX = (BY - bLeft) / kLeft;
        CX = (CY - bRight) / kRight;

        int[] X = {(int) AX, (int) BX, (int) CX, (int) DX};
        int[] Y = {(int) AY, (int) BY, (int) CY, (int) DY};

        Polygon lane = new Polygon(X, Y, 4);
        g2d.drawPolygon(lane);
        g2d.fillPolygon(lane);

        time += dt;
        if (time >= period) {
            time = 0;
        }
    }


}




































    /*public void incLane () {
        double B2 = BY;

        double j = (DX - AX) / (CX - BX);
        double l = (AY - BY) * j;

        BY = CY = B2; //+ (A2 - B2) / 2;
        AY = DY = AY + l;

        BX = (BY - bLeft) / kLeft;
        CX = (CY - bRight) / kRight;
        AX = (AY - bLeft) / kLeft;
        DX = (DY - bRight) / kRight;
    }*/
