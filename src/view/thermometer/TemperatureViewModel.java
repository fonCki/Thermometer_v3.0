package view.thermometer;

import javafx.application.Platform;
import javafx.beans.property.*;
import mediator.TemperatureModel;


import java.beans.PropertyChangeEvent;

public class TemperatureViewModel  {

    private DoubleProperty t1;
    private DoubleProperty t2;
    private DoubleProperty outside;



    private DoubleProperty temperature;

    TemperatureModel model;

    public TemperatureViewModel(TemperatureModel model) {
        this.t1 = new SimpleDoubleProperty();
        this.t2 = new SimpleDoubleProperty();
        this.outside = new SimpleDoubleProperty();
        this.temperature = new SimpleDoubleProperty();
        this.model = model;
        model.addListener(evt -> updateTemp(evt));
    }


    private void updateTemp(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            if (evt.getPropertyName().equals("t1")) t1.setValue((double) evt.getNewValue());
            if (evt.getPropertyName().equals("t2")) t2.setValue((double) evt.getNewValue());
            if (evt.getPropertyName().equals("outside")) outside.setValue((double) evt.getNewValue());
        });
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
