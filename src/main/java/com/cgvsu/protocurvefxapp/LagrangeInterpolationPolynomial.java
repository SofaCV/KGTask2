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

    //обработка некоторых возможных ошибок
    private void validatePoints(ArrayList<Point2D> points) {
        double eps = 1e-10;

        if (points.isEmpty()) {
            throw new IllegalArgumentException("Лист точек не может быть пустой");
        }

        if (points.size() > 1) {
            for (int i = 0; i < points.size(); i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    double x1 = points.get(i).getX();
                    double x2 = points.get(j).getX();

                    double y1 = points.get(i).getY();
                    double y2 = points.get(j).getY();
                    if (Math.abs(x1 - x2) < eps && Math.abs(y1 - y2) > eps) {
                        throw new IllegalArgumentException("Невозможно построить интерполяционный многочлен Лагранжа по " +
                                "двум точкам с одинаковыми координатами х, но разными координатами y");
                    }
                }
            }
        }
    }

    /**
     * @param gc
     * @param points - список точек, по которым строится график
     */
    @Override
    public void paint(GraphicsContext gc, ArrayList<Point2D> points) {
        validatePoints(points);

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

    //для теста (я не знаю, правильно это или нет, просто, делать методы публичными только из-за тестов мне показалось странным)
    public double calculationLagrangeInterpolationPolynomialTest(ArrayList<Point2D> points, double x){
        return calculationLagrangeInterpolationPolynomial(points, x);
    }
}