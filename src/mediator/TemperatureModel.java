package mediator;

import Radiator.Radiator;
import model.Temperature;
import util.PropertyChangeSubject;

public interface TemperatureModel extends PropertyChangeSubject {

  void addTemperature(String id, double temperature);
  Temperature getLastInsertedTemperature();
  Temperature getLastInsertedTemperature(String id);
  void updateTempOutside(double temp);

  void upRadiator();
  void downRadiator();
  int getRadiatorPower();
}
