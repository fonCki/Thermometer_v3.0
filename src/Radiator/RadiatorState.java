package Radiator;

public interface RadiatorState {
    public void turnUp(Radiator radiator);
    public void turnDown(Radiator radiator);
    public int getPower();
}
