package view.thermometer;

import core.ViewHandler;
import core.ViewModelFactory;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.ViewController;

public class TemperatureViewController implements ViewController {

   private ViewHandler viewHandler;
   private TemperatureViewModel temperatureViewModel;
   private StringProperty thermometerId;

   @FXML private Label labelOutside;
   @FXML private Label labelt1;
   @FXML private Label labelt2;
   @FXML private Label labelRadiator;

   public TemperatureViewController() {
   }

   public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
      this.viewHandler = viewHandler;
      this.temperatureViewModel = viewModelFactory.getTemperatureViewModel();


      labelRadiator.textProperty().bind(temperatureViewModel.radiatorProperty().asString());
      labelt1.textProperty().bind(temperatureViewModel.t1Property().asString());
      labelt2.textProperty().bind(temperatureViewModel.t2Property().asString());
      labelOutside.textProperty().bind(temperatureViewModel.outsideProperty().asString());
   }

   public void onUpButton(ActionEvent actionEvent) {
      temperatureViewModel.upRadiator();
   }

   public void onDownButton(ActionEvent actionEvent) {
      temperatureViewModel.downRadiator();
   }


   public void reset()
   {
      // empty
   }

}
