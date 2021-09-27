package view.thermometer;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TemperatureViewController {
   private TemperatureViewModel temperatureViewModel;

   @FXML private Label labelOutside;
   @FXML private Label labelT1;
   @FXML private Label labelT2;
   @FXML private Label labelRadiator;

   public void init(TemperatureViewModel temperatureViewModel) {
      this.temperatureViewModel = temperatureViewModel;
      labelRadiator.textProperty().bind(temperatureViewModel.radiatorPowerProperty().asString());
      labelT1.textProperty().bind(Bindings.format("%.1f",temperatureViewModel.t1Property()));
      labelT2.textProperty().bind(Bindings.format("%.1f",temperatureViewModel.t2Property()));
      labelOutside.textProperty().bind(Bindings.format("%.1f",temperatureViewModel.outsideProperty()));
   }
}
