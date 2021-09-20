package com.lyndexter.computer_graphics.lab3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Lab3 extends Application {

    public static double width = 600;
    public static double height = 400;

    private static final Group root = new Group();

    private static int pixelLineLength = 5;
    private static int pixelOutlineLength = 20;
    private static int interval = 10;
    private Color colorOutline = Color.GREEN;
    private Color colorLine = Color.RED;

    @Override
    public void start(Stage stage) throws Exception {

//        root.get
        stage.setTitle("Computer graphics Lab №3");
        drawBorder(width - 200, height);
        drawDashLine(width - 200, height);

        new BresenhamLine(0, 0, 0, 0).drawLine(root);


        Spinner<Integer> intervalInput = new Spinner<>(3, 10, 10);
        Label intervalLabel = new Label("Довжина проміжку в п-пікселях");
        intervalLabel.setLabelFor(intervalLabel);

        Spinner<Integer> pixelSizeLowerInput = new Spinner<>(1, 10, 5);
        Label pixelSizeLowerLabel = new Label("Довжина п-пікселя на діагоналі");
        pixelSizeLowerLabel.setLabelFor(pixelSizeLowerInput);

        Spinner<Integer> pixelSizeBiggerInput = new Spinner<>(1, 100, 20, 5);
        Label pixelSizeBiggerLabel = new Label("Довжина псевдопікселя на контурі");
        pixelSizeBiggerLabel.setLabelFor(pixelSizeBiggerInput);

        ColorPicker colorLowerInput = new ColorPicker();
        Label colorLowerLabel = new Label("Колір псевдопікселя на діагоналі");
        colorLowerLabel.setLabelFor(colorLowerInput);

        ColorPicker colorBiggerInput = new ColorPicker();
        Label colorBiggerLabel = new Label("Колір псевдопікселя на контурі");
        colorBiggerLabel.setLabelFor(colorBiggerInput);

        Button submitButton = new Button("Намалювати");


        VBox vBox = new VBox(intervalLabel, intervalInput, pixelSizeLowerLabel, pixelSizeLowerInput, pixelSizeBiggerLabel, pixelSizeBiggerInput, colorLowerLabel, colorLowerInput, colorBiggerLabel, colorBiggerInput, submitButton);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);


        submitButton.setOnAction(actionEvent -> {
            System.out.format("%s 44 %n", intervalInput.getValue());
            pixelLineLength = pixelSizeLowerInput.getValue();
            pixelOutlineLength = pixelSizeBiggerInput.getValue();
            interval = intervalInput.getValue();

            colorLine = colorLowerInput.getValue();
            colorOutline = colorBiggerInput.getValue();

            root.getChildren().clear();
            drawBorder(width - 200, height);
            drawDashLine(width - 200, height);
        });


        vBox.setMaxWidth(200);
        vBox.setMinWidth(200);
        vBox.setBackground(new Background(new BackgroundFill(Color.web("#4FC4EB"), CornerRadii.EMPTY, Insets.EMPTY)));
        Pane pane = new Pane(root);
        HBox stackPane = new HBox(vBox, pane);
        Scene scene = new Scene(stackPane, width, height);

        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            width = newSceneWidth.doubleValue();
            root.getChildren().clear();
            drawBorder(width - 200, height);
            drawDashLine(width - 200, height);

        });

        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            height = newSceneHeight.doubleValue();
            root.getChildren().clear();
            drawBorder(width - 200, height);
            drawDashLine(width - 200, height);
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Throwable {
        launch();
    }


    public void drawBorder(double width, double height) {
        new BresenhamLine(0, 0, 0, height - pixelOutlineLength, colorOutline, pixelOutlineLength).drawLine(root);
        new BresenhamLine(0, 0, width - pixelOutlineLength, 0, colorOutline, pixelOutlineLength).drawLine(root);
        new BresenhamLine(0, height - pixelOutlineLength, width, height - pixelOutlineLength, colorOutline, pixelOutlineLength).drawLine(root);
        new BresenhamLine(width - pixelOutlineLength, 0, width - pixelOutlineLength, height, colorOutline, pixelOutlineLength).drawLine(root);
    }

    public void drawDashLine(double width, double height) {
        BresenhamLine.setIntervalLength(interval);
        new BresenhamLine(0, 0, width, height, colorLine, pixelLineLength).drawDashLine(root);
        new BresenhamLine(0, height - pixelLineLength, width, 0, colorLine, pixelLineLength).drawDashLine(root);


    }
}

