package com.lyndexter.computer_graphics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static java.lang.Math.*;

public class Lab2 extends Application {

    public static int width = 600;
    public static int height = 240;

    private static final Group root = new Group();

    private static final int minTime = -100;
    private static final int maxTime = 100;
    private static final double timeInterval = 0.01;


    @Override
    public void start(Stage stage) throws Exception {


        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("function");


        lineChart.getData().add(series);
        Scene scene = new Scene(lineChart, width, height);
        stage.setTitle("Computer graphics Lab №2");
        DoubleStream.iterate(minTime, d -> d + timeInterval).limit((int) ((maxTime - minTime) / timeInterval) + 1).forEach(time -> {
            series.getData().add(new XYChart.Data<>(xFunction(time), yFunction(time)));
            System.out.format("%-10.5f-- %-5.5f %-10.1f %n", xFunction(time), yFunction(time), time);
        });
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.setCreateSymbols(false);
        lineChart.getData().get(0).getData().forEach(System.out::println);


//        Double min = DoubleStream.iterate(minTime, d -> d + timeInterval).limit((int) ((maxTime - minTime) / timeInterval) + 1).map(Lab2::xFunction).min().getAsDouble();
//        Double max = DoubleStream.iterate(minTime, d -> d + timeInterval).limit((int) ((maxTime - minTime) / timeInterval) + 1).map(Lab2::xFunction).max().getAsDouble();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Throwable {
        launch();
    }


    public static double xFunction(double time) {
        return 30 * cos(2 * time) * sqrt(abs(sin(2 * time)));
    }

    public static double yFunction(double time) {
        return 30 * sin(5 * time) * sqrt(abs(sin(2 * time)));
    }
//    public static double xFunction(double time) {
//        return 90* Math.sin(time)* Math.sqrt(Math.abs(Math.cos(2*time)));
//    }
//
//    public static double yFunction(double time) {
//        return 90* Math.cos(time)* Math.sqrt(Math.abs(Math.cos(2*time)));
//    }

}
