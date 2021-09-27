package mediator;

import Radiator.Radiator;
import model.Temperature;
import model.TemperatureList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TemperatureModelManager implements TemperatureModel {
   private TemperatureList temperatureList;
   private double tempOutside;
   private Radiator radiator;
   private PropertyChangeSupport changeSupport;

   public TemperatureModelManager() {
     temperatureList = new TemperatureList();
     changeSupport = new PropertyChangeSupport(this);
     radiator = new Radiator(changeSupport);
   }

  @Override
  public void addTemperature(String id, double value) {
    this.temperatureList.addTemperature(new Temperature(id, value));
    changeSupport.firePropertyChange(id, null, value);
  }


  @Override
  public void updateTempOutside(double temp) {
    tempOutside = temp;
    changeSupport.firePropertyChange("outside", null, temp);
  }

  @Override
  public double getOutsideTemp() {
    return tempOutside;
  }

  @Override
  public void upRadiator() {
    radiator.turnUp();
  }

  @Override
  public void downRadiator() {
    radiator.turnDown();
  }

  @Override
  public int getRadiatorPower() {
    return radiator.getPower();
  }

  @Override
  public void addListener(String eventName, PropertyChangeListener listener) {
    if (eventName.equals("") || eventName == null)  {
      addListener(listener);
    } else {
      changeSupport.addPropertyChangeListener(eventName, listener);
    }
  }

  @Override
  public void addListener(PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(listener);

  }

  @Override
  public void removeListener(String eventName, PropertyChangeListener listener) {
    if (eventName.equals("") || eventName == null) {
      removeListener(listener);
    } else {
      changeSupport.addPropertyChangeListener(eventName, listener);
    }
  }

  @Override
  public void removeListener(PropertyChangeListener listener) {
  changeSupport.removePropertyChangeListener(listener);
  }

}


