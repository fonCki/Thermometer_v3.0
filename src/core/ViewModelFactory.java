package core;

import view.thermometer.TemperatureViewModel;

public class ViewModelFactory {
    private ModelFactory modelFactory;
    private TemperatureViewModel temperatureViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public TemperatureViewModel getTemperatureViewModel() {
        if (this.temperatureViewModel == null) {
            this.temperatureViewModel = new TemperatureViewModel(modelFactory.getTemperatureModel());
        }
        return temperatureViewModel;
    }
}
