package ru.vsu.cs.smagina;

import static java.lang.Math.PI;
import static java.lang.Math.atan;

public class paramPicture {
    public static final double heightTL = 4;
    public static final double heightPerson = 3;
    public static final double lRoadMin = 200;
    public static final double lRoadMax = 300;
    public static final int height = 800;
    public static final double width = 800;
    public static final double widthRoad = 6;
    public static final double widthRectTL = 1.4;
    public static final double heightRectTL = 0.3;

    public static double getY(double l) {
        double tgH = l / heightPerson;
        double cornerOHL = Math.atan(tgH);
        return (cornerOHL / (PI / 2) * (height/2));
    }

    public static double getX(double l) {
        double x = Math.atan(widthRoad / 2 / (Math.sqrt(l * l + heightPerson * heightPerson))) / (atan(widthRoad / (2 * heightPerson))) * (width*1.2);
        return x;
    }
}
