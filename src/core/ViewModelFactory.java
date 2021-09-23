package core;

import view.master.RadiatorViewModel;
import view.thermometer.TemperatureViewModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private TemperatureViewModel temperatureViewModel;
    private RadiatorViewModel radiatorViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public TemperatureViewModel getTemperatureViewModel() {
        if (this.temperatureViewModel == null) {
            this.temperatureViewModel = new TemperatureViewModel(modelFactory.getTemperatureModel());
        }
        return temperatureViewModel;
    }

    public RadiatorViewModel getRadiatorViewModel() {
        if (this.radiatorViewModel == null) {
            this.radiatorViewModel = new RadiatorViewModel(modelFactory.getTemperatureModel());
        }
        return radiatorViewModel;
    }
}
