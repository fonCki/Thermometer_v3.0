package Radiator;

import java.beans.PropertyChangeSupport;

public class Radiator {
    private RadiatorState currentState;

    private PropertyChangeSupport changeSupport;

    public Radiator(PropertyChangeSupport changeSupport) {
        currentState = new OffState();
        this.changeSupport = changeSupport;
    }

    public void turnUp() {
        currentState.turnUp(this);
    }

    public void turnDown() {
        currentState.turnDown(this);
    }

    public int getPower() {
        System.out.println("Current Power: " + currentState.getPower());
        return currentState.getPower();
    }

    void setPowerState(RadiatorState state) {
        currentState = state;
        System.out.println("Temperature changed To: " + state.getPower());
        changeSupport.firePropertyChange("radiatorChange", null, getPower());
    }
}
