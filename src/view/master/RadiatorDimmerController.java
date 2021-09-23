package view.master;


import core.ViewHandler;
import core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mediator.TemperatureModel;
import view.thermometer.TemperatureViewModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RadiatorDimmerController {
   private RadiatorViewModel radiatorViewModel;
    @FXML private Label labelRadiator;



    public void init(RadiatorViewModel radiatorViewModel) {

        this.radiatorViewModel = radiatorViewModel;
        labelRadiator.textProperty().setValue("H");
        labelRadiator.textProperty().bind(radiatorViewModel.radiatorProperty().asString());

    }

    public void onUpButton(MouseEvent actionEvent) {
        radiatorViewModel.upRadiator();
    }

    public void onDownButton(MouseEvent actionEvent) {
        radiatorViewModel.downRadiator();
    }


}
