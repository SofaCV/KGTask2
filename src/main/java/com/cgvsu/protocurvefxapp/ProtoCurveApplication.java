package com.cgvsu.protocurvefxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class ProtoCurveApplication extends Application {
    LagrangeInterpolationPolynomial drawing = new LagrangeInterpolationPolynomial(Color.PINK);
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProtoCurveApplication.class.getResource("mainwindow.fxml"));
        fxmlLoader.setControllerFactory(param -> new ProtoCurveController(drawing));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Proto Curve App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}