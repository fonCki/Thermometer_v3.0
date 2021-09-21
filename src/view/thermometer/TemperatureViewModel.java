package view.thermometer;

import javafx.application.Platform;
import javafx.beans.property.*;
import mediator.TemperatureModel;


import java.beans.PropertyChangeEvent;

public class TemperatureViewModel  {

    private DoubleProperty t1;
    private DoubleProperty t2;
    private DoubleProperty outside;

    private IntegerProperty radiator;

    private DoubleProperty temperature;

    TemperatureModel model;

    public TemperatureViewModel(TemperatureModel model) {
        this.t1 = new SimpleDoubleProperty();
        this.t2 = new SimpleDoubleProperty();
        this.outside = new SimpleDoubleProperty();
        this.temperature = new SimpleDoubleProperty();

        this.radiator = new SimpleIntegerProperty(0);
        this.model = model;
        model.addListener(evt -> updateValues(evt));
    }

    private void  updateValues(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("radiatorChange")) {
                updateRadiator(evt);
            } else {
                updateTemp(evt);
            }
    }

    private void updateRadiator(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            radiator.setValue((int) evt.getNewValue());
        });
    }

    private void updateTemp(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            if (evt.getPropertyName().equals("t1")) t1.setValue((double) evt.getNewValue());
            if (evt.getPropertyName().equals("t2")) t2.setValue((double) evt.getNewValue());
            if (evt.getPropertyName().equals("outside")) outside.setValue((double) evt.getNewValue());
        });
    }

    public IntegerProperty radiatorProperty() {
        return radiator;
    }

    public void upRadiator() {
        model.upRadiator();
    }

    public void downRadiator() {
        model.downRadiator();
    }


    public DoubleProperty t1Property() {
        return t1;
    }

    public DoubleProperty t2Property() {
        return t2;
    }

    public DoubleProperty outsideProperty(){
        return outside;
    }

}
