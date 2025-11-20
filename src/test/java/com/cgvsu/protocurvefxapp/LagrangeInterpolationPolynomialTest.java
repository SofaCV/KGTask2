package com.cgvsu.protocurvefxapp;

import javafx.scene.paint.Color;
import org.junit.Test;

import javafx.geometry.Point2D;
import java.lang.reflect.Method;
import java.util.ArrayList;


import static org.junit.Assert.assertEquals;

public class LagrangeInterpolationPolynomialTest {

    private LagrangeInterpolationPolynomial polynomial = new LagrangeInterpolationPolynomial(Color.PINK);;
    private ArrayList<Point2D> points = new ArrayList<>();

    //проверка работы конструктора
    @Test
    public void checkConstructor(){
        assertEquals(Color.PINK, polynomial.getColorLine());
    }

    //проверка работы функции calculationLagrangeInterpolationPolynomial, которая считает значение функции в точке
    //я использовала рефлексию
    @Test
    public void checkCalculationLagrangeInterpolationPolynomial() {
        try {
            points.add(new Point2D(0,0));
            points.add(new Point2D(6,6));
            points.add(new Point2D(0.35,0.25));
            Method method = LagrangeInterpolationPolynomial.class.getDeclaredMethod(
                    "calculationLagrangeInterpolationPolynomial",
                    ArrayList.class,
                    double.class
            );
            method.setAccessible(true);

            double result1 = (double) method.invoke(polynomial, points, 0.0);
            double result2 = (double) method.invoke(polynomial, points, 6.0);
            double result3 = (double) method.invoke(polynomial, points, 0.35);

            assertEquals(0, result1, 10e-8);
            assertEquals(6, result2, 10e-8);
            assertEquals(0.25, result3, 10e-8);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
