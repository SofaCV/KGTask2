package com.cgvsu.protocurvefxapp;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class LagrangeInterpolationPolynomial implements Drawing {
    private Color colorLine;

    public LagrangeInterpolationPolynomial(Color color){
        this.colorLine = color;
    }

    //для теста
    public Color getColorLine() {
        return colorLine;
    }

    private double calculationBasicPolynomials(ArrayList<Point2D> points, double xi, double x, int index) {
        double numerator = 1;
        double denominator = 1;

        for (int i = 0; i < points.size(); i++) {
            if (i == index) {
                continue;
            }
            numerator *= (x - points.get(i).getX());
            denominator *= (xi - points.get(i).getX());
        }
        return numerator / denominator;
    }

    private double calculationLagrangeInterpolationPolynomial(ArrayList<Point2D> points, double x) {
        double summand = 0;
        for (int i = 0; i < points.size(); i++) {
            summand += points.get(i).getY() * calculationBasicPolynomials(points, points.get(i).getX(), x, i);
        }
        return summand;
    }

    /**
     * @param gc
     * @param points - список точек, по которым строится график
     */
    @Override
    public void paint(GraphicsContext gc, ArrayList<Point2D> points) {
        double minX = points.get(0).getX();
        double maxX = points.get(0).getX();

        for (Point2D point : points) {
            minX = Math.min(minX, point.getX());
            maxX = Math.max(maxX, point.getX());
        }
        gc.beginPath();
        gc.moveTo(minX, calculationLagrangeInterpolationPolynomial(points, minX));

        double x = minX;
        while (x < maxX){
            x ++;
            gc.lineTo(x, calculationLagrangeInterpolationPolynomial(points, x));
        }
        if(x != maxX){
            gc.lineTo(x, calculationLagrangeInterpolationPolynomial(points, x));
        }

        gc.setStroke(this.colorLine);
        gc.setLineWidth(2);
        gc.stroke();
    }


}