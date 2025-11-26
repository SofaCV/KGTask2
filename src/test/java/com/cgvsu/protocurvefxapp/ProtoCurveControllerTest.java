package com.cgvsu.protocurvefxapp;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.assertSame;

public class ProtoCurveControllerTest {
    LagrangeInterpolationPolynomial testPolynomial;
    @Before
    public void setUp() {
        testPolynomial = new LagrangeInterpolationPolynomial(Color.PINK);
    }

    //проверка конструктора
    @Test
    public void checkConstructor(){
        ProtoCurveController testController = new ProtoCurveController(testPolynomial);
        Drawing testChart = testController.getChart();

        assertSame(testChart,testPolynomial);

    }

}
