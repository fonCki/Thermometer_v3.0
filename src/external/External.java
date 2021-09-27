package external;

import mediator.TemperatureModel;
import external.Temperature;

public class External extends Temperature{
    private double t, min, max;
    private TemperatureModel model;


    public External(TemperatureModel model, double t, double min, double max) {
        this.t = t;
        this.min = min;
        this.max = max;
        this.model = model;
    }

    @Override
    public void run() {
        while (true){
            t = external(t,min, max);
            model.updateTempOutside(t);
            try {
                Thread.sleep(1000 * (int) Math.floor(Math.random()*(Temperature.MAX_SECONDS - Temperature.MIN_SECONDS + 1)+ Temperature.MIN_SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double external(double t0, double min, double max)
    {
        double left = t0 - min;
        double right = max - t0;
        int sign = Math.random() * (left + right) > left ? 1 : -1;
        t0 += sign * Math.random();
        return t0;
    }

}
