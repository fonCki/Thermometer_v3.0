package core;

import mediator.TemperatureModel;
import mediator.TemperatureModelManager;

public class ModelFactory {
    private TemperatureModel temperatureModel;

    public TemperatureModel getTemperatureModel() {
        if (temperatureModel == null)
            temperatureModel = new TemperatureModelManager();
        return temperatureModel;
    }

}
