package com.lyndexter.computer_graphics.lab4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static java.lang.Math.*;


public class Lab4 extends Application {

    public static int diameter = 36;
    public static int threadLength = 120;
    public static int angle = 90;

    public static int width = 600;
    public static int height = 400;
    public static int x = width / 2;
    public static int y = height / 2;
    public static double angleA = 0.125 * 2 * PI + 0.005;

    private static int sign = 1;
    public static Group root = new Group();

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        Scene scene = new Scene(root, width, height);
        stage.setTitle("Computer graphics Lab №4");

        drawPicture(x, y);

        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, (EventHandler<javafx.event.ActionEvent>) Lab4::refreshScene),
                new KeyFrame(Duration.millis(10))
        );

        timeline.setCycleCount(Timeline.INDEFINITE);

        stage.setScene(scene);
        stage.show();
        timeline.play();
    }

    public static void main(String[] args) throws Throwable {
        launch();
    }

    private static void refreshScene(Event event) {

        double B = 0.004 * 2 * PI * (-abs((angleA - PI / 2) * 4) / PI + 1 + 0.02);
//            double B = 0.002 *2*PI;
        angleA += sign * B;

        if (angleA >= 0.375 * 2 * PI) {
            sign = -1;
        } else if (angleA <= 0.125 * 2 * PI) {
            sign = 1;
        }

        x = (int) (width / 2 - (threadLength) * cos(angleA));
        y = (int) ((height - threadLength) / 2 + (threadLength) * sin(angleA));
        drawPicture(x, y);
    }

    private static void drawPicture(int x, int y) {
        root.getChildren().clear();
        Circle circle1 = new Circle(x, y, (int) (diameter / 2));
        circle1.setFill(Color.BLUE);
        Line line = new Line(x, y, (int) (width / 2), (double) (height - threadLength) / 2);
        line.setStroke(Color.web("#D47619"));
        Circle dot = new Circle((int) (width / 2), (double) (height - threadLength) / 2, (int) (4));
        dot.setFill(Color.BROWN);
        root.getChildren().addAll(line, circle1, dot);
    }
}
