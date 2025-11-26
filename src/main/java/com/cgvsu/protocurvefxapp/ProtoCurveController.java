package com.cgvsu.protocurvefxapp;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;

public class ProtoCurveController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    private Drawing chart;

    public ProtoCurveController(Drawing ch) {
       this.chart = ch;
    }

    //для теста
    public Drawing getChart() {
        return chart;
    }

    ArrayList<Point2D> points = new ArrayList<>();




    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        canvas.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY -> handlePrimaryClick(canvas.getGraphicsContext2D(), event);
            }
        });

    }

    private void handlePrimaryClick(GraphicsContext graphicsContext, MouseEvent event) {
        final Point2D clickPoint = new Point2D(event.getX(), event.getY());
        points.add(clickPoint);
        redrawAll(graphicsContext);

    }

    private void redrawAll(GraphicsContext graphicsContext) {
        clearCanvas(graphicsContext);

        final int POINT_RADIUS = 3;
        for (Point2D point : points) {
            graphicsContext.fillOval(
                    point.getX() - POINT_RADIUS, point.getY() - POINT_RADIUS,
                    2 * POINT_RADIUS, 2 * POINT_RADIUS);
        }
        if(points.size() > 1){
            chart.paint(graphicsContext, points);
        }
    }

    private void clearCanvas(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}