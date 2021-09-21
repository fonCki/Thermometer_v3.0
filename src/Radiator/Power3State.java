package Radiator;

public class Power3State implements RadiatorState{
    private static final int POWER = 3;
    Thread newThread;

    public Power3State(Radiator radiator) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Deacresed manualy...");
            }
            radiator.setPowerState(new Power2State());
        };
        newThread = new Thread(runnable);
        newThread.setDaemon(true);
        newThread.start();
    }

    @Override
    public void turnUp(Radiator radiator) {

    }

    @Override
    public void turnDown(Radiator radiator) {
            newThread.interrupt();
    }

    @Override
    public int getPower() {
        return POWER;
    }

}
