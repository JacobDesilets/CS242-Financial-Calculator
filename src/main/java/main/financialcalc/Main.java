package main.financialcalc;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Financial Calculator extra credit for CS242
 * @author Jacob Desilets
 * @author Clayton Knewasser
 */
public class Main extends Application {
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #32a852;");

        TextField principalTF = new TextField("1000");
        Label principalLabel = new Label("Principal ammount: ");
        principalLabel.setLabelFor(principalTF);

        TextField monthsTF = new TextField("12");
        Label monthsLabel = new Label("Time in months: ");
        monthsLabel.setLabelFor(monthsTF);

        Slider interestSlider = new Slider();
        Label interestLabel = new Label("Interest rate: 0");
        interestLabel.setLabelFor(interestSlider);

        interestSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double value = (double) observableValue.getValue();
                interestLabel.setText("Interest rate: " + value / 100);
            }
        });

        ToggleGroup tg = new ToggleGroup();
        RadioButton simpleRB = new RadioButton("Simple interest");
        simpleRB.setToggleGroup(tg);
        simpleRB.setSelected(true);
        RadioButton compoundRB = new RadioButton("Compound interest");
        compoundRB.setToggleGroup(tg);

        Label keyLabel = new Label("Press F1 to toggle between interest methods.");

        HBox output = new HBox();

        Button computeButton = new Button("Compute Interest");
        Label outputLabel = new Label("Interest: ");

        computeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean isSimpleInterest = simpleRB.isSelected();
                double principal = Double.parseDouble(principalTF.getText());
                int time = Integer.parseInt(monthsTF.getText());
                double rate = interestSlider.getValue() / 100;
                double ammount = 0.0;

                if (isSimpleInterest) {
                    ammount = principal * (1 + (rate * time));
                }  else {
                    ammount = principal * (Math.pow(1 + rate, time));
                }

                outputLabel.setText(String.valueOf(ammount));
            }
        });

        output.getChildren().addAll(computeButton, outputLabel);

        root.getChildren().addAll(principalLabel, principalTF, monthsLabel, monthsTF, interestLabel, interestSlider, simpleRB, compoundRB, keyLabel,
                output);

        Scene scene = new Scene(root, 400, 400);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.F1) {
                simpleRB.setSelected(!(simpleRB.isSelected()));
                compoundRB.setSelected(!(simpleRB.isSelected()));
            }
        });

        primaryStage.setTitle("Financial Calculator - Jacob Desilets & Clayton Knewasser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
