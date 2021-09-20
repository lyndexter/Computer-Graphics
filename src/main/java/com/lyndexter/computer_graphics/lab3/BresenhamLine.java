package com.lyndexter.computer_graphics.lab3;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.*;

public class BresenhamLine {
    private Color color = Color.BLACK;
    private double length;
    private double multiplier;
    private static int pixelSize = 5;
    private static double intervalLength = 10;

    private double base;
    private double function;

    public BresenhamLine(double x1, double y1, double x2, double y2, Color color, int pixelSize) {
        this(x1, y1, x2, y2);
        this.color = color;
        BresenhamLine.pixelSize = pixelSize;
    }

    public BresenhamLine(double x1, double y1, double x2, double y2) {
        double xLength = x2 - x1;
        double yLength = y2 - y1;
        length = max(abs(xLength), abs(yLength));


        multiplier = (double) yLength / xLength;
        double y, x;
        if (abs(multiplier) > 1) {
            y = min(y1, y2);
            x = y == y1 ? x1 : x2;
        } else {
            x = min(x1, x2);
            y = x == x1 ? y1 : y2;
        }
        base = x;
        function = y;
    }

    public void drawLine(Group root) {
        drawLine(root, length);
    }


    public void drawLine(Group root, double length) {
        if (length == 0) {
            Rectangle pixel = new Rectangle(base, function, pixelSize, pixelSize);
            pixel.setFill(color);
            root.getChildren().add(pixel);
            return;
        }
        length += pixelSize;
        while ((length -= pixelSize) >= 0) {
            Rectangle pixel = new Rectangle(round(base / pixelSize) * pixelSize, round(function / pixelSize) * pixelSize, pixelSize, pixelSize);
            pixel.setFill(color);
//            System.out.format("  %-10.5f %-10.5f%n", base, function);
            root.getChildren().add(pixel);
            base += abs(multiplier) > 1 ? pixelSize / multiplier : pixelSize;
            function += abs(multiplier) > 1 ? pixelSize : multiplier * pixelSize;
        }
    }

    public void drawDashLine(Group root) {
        double tempLength = 0;
        while (tempLength < length) {
            drawLine(root, (intervalLength - 1) * pixelSize);
            tempLength += 3 * intervalLength * pixelSize / 2;
            double erer = intervalLength * pixelSize / 2;
            base += abs(multiplier) > 1 ? erer / multiplier : erer;
            function += abs(multiplier) > 1 ? erer : multiplier * erer;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static void setPixelSize(int pixelSize) {
        BresenhamLine.pixelSize = pixelSize;
    }

    public static void setIntervalLength(int interval) {
        intervalLength = interval;
    }

    @Override
    public String toString() {
        return "BresenhamLine{}";
    }
}
