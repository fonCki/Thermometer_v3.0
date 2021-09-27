package external;

import mediator.TemperatureModel;

public class Thermometer extends Temperature{
    private String id;
    private double t;
    private int d;
    private TemperatureModel model;

    public Thermometer(TemperatureModel model, String id, double t, int d) {
        this.id = id;
        this.t = t;
        this.d = d;
        this.model = model;
    }


    @Override
    public void run() {
        while (true) {
            t = temperature(t, model.getRadiatorPower(), d, 0, 6);
            model.addTemperature(id, t);
            try {
                Thread.sleep(1000 * (int) Math.floor(Math.random()*(Temperature.MAX_SECONDS - Temperature.MIN_SECONDS + 1)+ Temperature.MIN_SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private double temperature(double t, int p, int d, double t0, int s) {

        double tMax = Math.min(11 * p + 10, 11 * p + 10 + t0);
        tMax = Math.max(Math.max(t, tMax), t0);
        double heaterTerm = 0;
        if (p > 0) {
            double den = Math.max((tMax * (20 - 5 * p) * (d + 5)), 0.1);
            heaterTerm = 30 * s * Math.abs(tMax - t) / den;
        }
        double outdoorTerm = (t - t0) * s / 250.0;
        t = Math.min(Math.max(t - outdoorTerm + heaterTerm, t0), tMax);

        return t;
    }
    

}

