package external;

import mediator.TemperatureModel;

public class ExternalTemperature implements Runnable{
    private double t, min, max;
    private TemperatureModel model;

    public ExternalTemperature(TemperatureModel model, double t, double min, double max) {
        this.t = t;
        this.min = min;
        this.max = max;
        this.model = model;
    }

    @Override
    public void run() {
        while (true){
            t = externalTemperature(t,min, max);
            model.updateTempOutside(t);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double externalTemperature(double t0, double min, double max)
    {
        double left = t0 - min;
        double right = max - t0;
        int sign = Math.random() * (left + right) > left ? 1 : -1;
        t0 += sign * Math.random();
        return t0;
    }

}
