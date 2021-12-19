module main.financialcalc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens main.financialcalc to javafx.fxml;
    exports main.financialcalc;
}