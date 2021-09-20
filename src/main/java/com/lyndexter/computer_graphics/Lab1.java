package com.lyndexter.computer_graphics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.stream.IntStream;


public class Lab1 extends Application {

    public static int period = 40;
    public static int amplitude = 20;
    public static int impulseTime = 15;

    public static int width = 600;
    public static int height = 240;

    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, width, height);
        stage.setTitle("Computer graphics Lab №1");

        int x = width/2-5*period, y = height / 2;
        for (int repetition = 0; repetition < 10; repetition++) {

            root.getChildren().add(drawImpulse(x, y));
            x += period;

        }


        stage.setScene(scene);
        stage.show();
    }

    public static Path drawImpulse(int x, int y) {

        Path impulse = new Path();
        MoveTo moveTo = new MoveTo(x, y);
        LineTo line1 = new LineTo(x, (y -= amplitude));
        LineTo line2 = new LineTo(x += impulseTime, y);
        LineTo line3 = new LineTo(x, y += amplitude);
        LineTo line4 = new LineTo(x -= (impulseTime - period), y);
        impulse.getElements().addAll(moveTo, line1, line2, line3, line4);
        return impulse;
    }

    public static void main(String[] args) throws Throwable {
        Reader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        System.out.println("Please write period");
        period = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Please write amplitude");
        amplitude = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Please write impulse time");
        impulseTime = Integer.parseInt(bufferedReader.readLine());
        if (impulseTime >= period) {
            throw new Throwable("impulse time is bigger than period");
        }
        width = period * 10 + 100;
        height = height / 2 < amplitude ? amplitude * 2 + 50 : height;
        launch();

    }
}