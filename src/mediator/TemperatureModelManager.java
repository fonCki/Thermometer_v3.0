package mediator;

import Radiator.Radiator;
import model.Temperature;
import model.TemperatureList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class TemperatureModelManager implements TemperatureModel {
   private TemperatureList temperatureList;
   private Radiator radiator;

   private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

   public TemperatureModelManager() {
     temperatureList = new TemperatureList();
     radiator = new Radiator(changeSupport);
   }

  @Override public void addTemperature(String id, double value)
  {
    Temperature temperature = new Temperature(id, value);
    Temperature old = getLastInsertedTemperature();
    this.temperatureList.addTemperature(temperature);
    if (old != null && old.getValue() != temperature.getValue()) {
      System.out.println("-->" + temperature + " (from: " + old + ")");
      changeSupport.firePropertyChange(temperature.getId(), old.getValue(), temperature.getValue());
    }
  }

  @Override public Temperature getLastInsertedTemperature()
  {
    return temperatureList.getLastTemperature(null);
  }

  @Override public Temperature getLastInsertedTemperature(String id)
  {
    return temperatureList.getLastTemperature(id);
  }

  @Override
  public void updateTempOutside(double temp) {
    changeSupport.firePropertyChange("outside", null, temp);
  }

  @Override
  public void upRadiator() {
    radiator.turnUp();
 //   changeSupport.firePropertyChange("radiatorChange", null, radiator.getPower()); change here? or in the radiator?
  }

  @Override
  public void downRadiator() {
    radiator.turnDown();
 //   changeSupport.firePropertyChange("radiatorChange", null, radiator.getPower()); change here? or in the radiator?
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


  @Override
  public int getRadiatorPower() {
    return radiator.getPower();
  }
}


