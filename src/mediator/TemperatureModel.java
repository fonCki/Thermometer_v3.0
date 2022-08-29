package mediator;

import util.PropertyChangeSubject;

public interface TemperatureModel extends PropertyChangeSubject {

  //About Temperature//
  void addTemperature(String id, double temperature);
  void updateTempOutside(double temp);
  double getOutsideTemp();

  //About Radiator
  void upRadiator();
  void downRadiator();
  int getRadiatorPower();

}
