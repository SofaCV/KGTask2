package com.cgvsu.protocurvefxapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import javafx.geometry.Point2D;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LagrangeInterpolationPolynomialTest {
    private LagrangeInterpolationPolynomial testPolynomial = new LagrangeInterpolationPolynomial(Color.PINK);
    private ArrayList<Point2D> points;

    @Before
    public void setUp() {
        points = new ArrayList<>();
    }

    //проверка работы конструктора
    @Test
    public void checkConstructor(){
        assertEquals(Color.PINK, testPolynomial.getColorLine());
    }

    //проверка работы функции calculationLagrangeInterpolationPolynomial
    @Test
    public void checkCalculationLagrangeInterpolationPolynomial() {

        points.add(new Point2D(0,0));
        points.add(new Point2D(6,6));
        points.add(new Point2D(0.35,0.25));

        assertEquals(0, testPolynomial.calculationLagrangeInterpolationPolynomialTest(points,0), 10e-8);
        assertEquals(6, testPolynomial.calculationLagrangeInterpolationPolynomialTest(points,6), 10e-8);
        assertEquals(0.25, testPolynomial.calculationLagrangeInterpolationPolynomialTest(points,0.35), 10e-8);
    }

    //проверка для метода paint, когда список точек пуст
    @Test
    public void checkListPointsEmpty(){
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> testPolynomial.paint(gc, points)
        );

        assertEquals("Лист точек не может быть пустой", exception.getMessage());
    }

    //проверка для метода paint, когда в списке находятся точки с одинаковыми координатами х, но разными y
    @Test
    public void checkRepeatedX(){
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        points.add(new Point2D(6.5,9));
        points.add(new Point2D(6.5,12));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> testPolynomial.paint(gc, points)
        );

        assertEquals("Невозможно построить интерполяционный многочлен Лагранжа по двум точкам с одинаковыми " +
                "координатами х, но разными координатами y", exception.getMessage());


    }
}
