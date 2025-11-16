package com.cgvsu.protocurvefxapp;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public interface Drawing {
    void paint(GraphicsContext gc, ArrayList<Point2D> points);
}
