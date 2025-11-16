module com.cgvsu.rasterizationfxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.cgvsu.protocurvefxapp to javafx.fxml;
    exports com.cgvsu.protocurvefxapp;
}