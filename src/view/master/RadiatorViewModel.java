package view.master;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import mediator.TemperatureModel;

import java.beans.PropertyChangeEvent;

public class RadiatorViewModel {
    private IntegerProperty radiatorPower;
    TemperatureModel model;

    public RadiatorViewModel(TemperatureModel model) {
        this.radiatorPower = new SimpleIntegerProperty(0);
        this.model = model;
        model.addListener(evt -> updateRadiator(evt));
    }

    private void updateRadiator(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            if (evt.getPropertyName().equals("radiatorChange"))
                radiatorPower.setValue((int) evt.getNewValue());
        });
    }

    public void upRadiator() {
        model.upRadiator();
    }

    public void downRadiator() {
        model.downRadiator();
    }

    public IntegerProperty radiatorProperty() {
        return radiatorPower;
    }

}
