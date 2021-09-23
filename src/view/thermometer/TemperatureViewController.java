package view.thermometer;

import core.ViewHandler;
import core.ViewModelFactory;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TemperatureViewController {

   private ViewHandler viewHandler;
   private TemperatureViewModel temperatureViewModel;
   private StringProperty thermometerId;

   @FXML private Label labelOutside;
   @FXML private Label labelT1;
   @FXML private Label labelT2;
   @FXML private Label labelRadiator;

   public TemperatureViewController() {
   }

   public void init(ViewModelFactory viewModelFactory) { //Should I import the whole viewmodel factory? I need to cross the infprmation about the radiator power

      this.temperatureViewModel = viewModelFactory.getTemperatureViewModel(); // Is this wright?

      labelRadiator.textProperty().bind(viewModelFactory.getRadiatorViewModel().radiatorProperty().asString());
      labelT1.textProperty().bind(Bindings.format("%.1f",temperatureViewModel.t1Property()));
      labelT2.textProperty().bind(Bindings.format("%.1f",temperatureViewModel.t2Property()));
      labelOutside.textProperty().bind(Bindings.format("%.1f",temperatureViewModel.outsideProperty()));
   }



}
