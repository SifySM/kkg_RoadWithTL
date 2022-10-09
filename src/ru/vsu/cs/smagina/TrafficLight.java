package ru.vsu.cs.smagina;

import java.awt.*;

import static java.lang.Math.PI;
import static java.lang.Math.atan;
import static ru.vsu.cs.smagina.paramPicture.*;

public class TrafficLight {
    private static final Color RED_COLOR = Color.RED;
    private static final Color OFF_COLOR = Color.pink;
    private static final TrafficLightState[] programTL = new TrafficLightState[]{
            new TrafficLightState(true, false, false, 5),
            new TrafficLightState(true, true, false, 2),
            new TrafficLightState(false, false, true, 5),
            new TrafficLightState(false, true, true, 2)
    };

    int widthRectTL;
    int heightRectTL;
    int BXRoundRect;
    int BYRoundRect;
    int ovalsY;
    int ovalD;
    int oval1X;
    int oval2X;
    int oval3X;

    private int ColorCount = 0;
    private int time = 0;

    private int startTime = 0;
    private int currentState = 0;
    private double velocity = 60;
    private double lRoad;
    private int AX;
    private double AY;
    private int BX;
    private double BY;
    private int CX;
    private double CY;
    private int DX;
    private double DY;

    public TrafficLight(double velocity) {
        setVelocity(velocity);
    }

    private void drawTrafficLight(Graphics g, int[] arrayX) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);

        double l = lRoad - velocity * time;

        if (l <= 0) {
            lRoad = (Math.random() * (lRoadMax - lRoadMin) + lRoadMin);
            //lRoad = (lRoad == lRoadMax ? lRoadMin : lRoadMax);
            l = lRoad;
            time = 0;
            ovalD = 0;
            startTime = 0;
        }

        double startY = paramPicture.getY(l);
        double dYTL = getHeightTLOnL(l);

        AY = DY = height - startY;

        BY = CY = AY - dYTL;
        AX = BX = arrayX[(int) startY];
        DX = CX = arrayX[802 - (int) startY];

        double k = (CX - BX) / widthRoad; //пикселей на метр

        if (AY < 420) g2d.setStroke(new BasicStroke(1));
        else if (AY < 450) g2d.setStroke(new BasicStroke(2));
        else if (AY < 470) g2d.setStroke(new BasicStroke(3));
        else if (AY < 500) g2d.setStroke(new BasicStroke(4));
        else g2d.setStroke(new BasicStroke(5));

        g2d.drawLine(AX, (int) AY, AX, (int) BY);

        g2d.drawLine(DX, (int) DY, CX, (int) CY);

        g2d.drawLine(BX, (int) BY, CX, (int) CY);

        widthRectTL = (int) (k * paramPicture.widthRectTL); //to pix
        heightRectTL = (int) (k * paramPicture.heightRectTL);

        BXRoundRect = BX + (CX - BX - widthRectTL) / 2;
        BYRoundRect = (int) (BY - heightRectTL / 3);

        if (heightRectTL >= 1) {

            g2d.drawRoundRect(BXRoundRect, BYRoundRect, widthRectTL, heightRectTL, (int) (0.4 * widthRectTL), (int) (0.4 * heightRectTL));
            g2d.setColor(new Color(84, 84, 84));
            g2d.fillRoundRect(BXRoundRect, BYRoundRect, widthRectTL, heightRectTL, (int) (0.4 * widthRectTL), (int) (0.4 * heightRectTL));

            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(2));

            oval1X = BXRoundRect + (widthRectTL - 3 * heightRectTL) / 2;
            oval2X = oval1X + heightRectTL;
            oval3X = oval2X + heightRectTL;

            ovalsY = (int) (BYRoundRect + 0.1 * heightRectTL);
            ovalD = (int) (0.8 * heightRectTL);

            g2d.drawOval(oval1X, ovalsY, ovalD, ovalD);
            g2d.drawOval(oval2X, ovalsY, ovalD, ovalD);
            g2d.drawOval(oval3X, ovalsY, ovalD, ovalD);

        }

        g2d.setStroke(new BasicStroke(1));
    }

    public void setColorTL(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (programTL[currentState].red){
            g2d.setColor(Color.red);
        } else g2d.setColor(new Color(58, 45, 45));
        g2d.fillOval(oval1X, ovalsY, ovalD, ovalD);

        if (programTL[currentState].green){
            g2d.setColor(Color.green);
        } else g2d.setColor(new Color(58, 66, 58));
        g2d.fillOval(oval3X, ovalsY, ovalD, ovalD);

        if (programTL[currentState].yellow){
            g2d.setColor(Color.yellow);
        } else g2d.setColor(new Color(63, 62, 51, 255));
        g2d.fillOval(oval2X, ovalsY, ovalD, ovalD);
       /* switch (colorTL) {
            case RED -> {
                //g2d.setColor(program[currentState].isRead() ? RED_COLOR : OFF_COLOR);

                g2d.setColor(new Color(255, 0, 0));
                g2d.fillOval(324, 294, 42, 42);
                g2d.setColor(new Color(61, 57, 38, 203));
                g2d.fillOval(374, 294, 42, 42);
                g2d.setColor(new Color(58, 66, 58));
                g2d.fillOval(424, 294, 42, 42);
                break;
            }

            case REDandYELLOW -> {
                g2d.setColor(new Color(255, 0, 0));
                g2d.fillOval(324, 294, 42, 42);
                g2d.setColor(new Color(255, 255, 0, 255));
                g2d.fillOval(374, 294, 42, 42);
                g2d.setColor(new Color(58, 66, 58));
                g2d.fillOval(424, 294, 42, 42);
                break;
            }

            case YELLOWandGREEN -> {
                g2d.setColor(new Color(58, 45, 45));
                g2d.fillOval(324, 294, 42, 42);
                g2d.setColor(new Color(255, 255, 0, 255));
                g2d.fillOval(374, 294, 42, 42);
                g2d.setColor(new Color(0, 255, 0));
                g2d.fillOval(424, 294, 42, 42);
                break;
            }

            case GREEN -> {
                g2d.setColor(new Color(44, 35, 35));
                g2d.fillOval(324, 294, 42, 42);
                g2d.setColor(new Color(63, 62, 51, 255));
                g2d.fillOval(374, 294, 42, 42);
                g2d.setColor(new Color(0, 255, 0));
                g2d.fillOval(424, 294, 42, 42);
                break;
            }
        }*/
    }


    public void setVelocity(double velocity) {
        //  period = (int) (2 * l0 * 3600 / velocity);
        this.velocity = velocity / 3600; // перевод в метры/мс
    }

    public void draw(Graphics g, int deltaTime, int[] arrayX) {
        drawTrafficLight(g, arrayX);

        time += deltaTime;
        if (time >= startTime + programTL[currentState].time * 1000) {
            currentState += 1;
            if (currentState == programTL.length) currentState = 0;
            startTime = time;
        }

        setColorTL(g);
    }

    public void update() {

    }

    private double getHeightTLOnL(double l) {
        double heightTLOnL = (atan((heightTL - heightPerson) / l) + atan(heightPerson / l)) / PI * (height);
        return heightTLOnL;
    }
}
