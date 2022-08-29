package core;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.master.RadiatorDimmerController;
import view.thermometer.TemperatureViewController;

import java.io.IOException;

public class ViewHandler {
  private final ViewModelFactory viewModelFactory;
  private RadiatorDimmerController radiatorDimmerController;
  private TemperatureViewController temperatureViewController;

  private double xOffset = 0; //Moving the stage without title bar...
  private double yOffset = 0; //Moving the stage without title bar...


  public ViewHandler(ViewModelFactory viewModelFactory) {
    this.viewModelFactory = viewModelFactory;
  }

  public void start() throws IOException {
    Stage stage = new Stage();
    Stage stage2 = new Stage();

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/master/RadiatorDimmer.fxml"));
    Parent root = loader.load();

    radiatorDimmerController = loader.getController();
    radiatorDimmerController.init(viewModelFactory.getRadiatorViewModel());

    Scene scene = new Scene(root);
    stage.setScene(scene);

    //-----The dimmer controlled is located en the center-right-----------------------/////
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) * 0.3;
    double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) * 0.6;
    stage.setX(x);
    stage.setY(y);
    //---------------------------------------------------////

    stage.initStyle(StageStyle.UNDECORATED); // the title and close are eliminated

    //--- make possible to move the window draggin in any part of the stage---//
    root.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
      }
    });
    root.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
      }
    });
    //---------------------------------------------------////

    stage.show();
    //-------------------Finished the first View (Dimmer/Radiator)--------------------------------------------------//


    FXMLLoader loader2 = new FXMLLoader();
    loader2.setLocation(getClass().getResource("../view/thermometer/TemperatureView.fxml"));
    Parent root2 = loader2.load();

    TemperatureViewController ctrl2 = loader2.getController();
    temperatureViewController = ctrl2;
    temperatureViewController.init(viewModelFactory.getTemperatureViewModel());


    //---------------------------------------------------////

    stage2.initStyle(StageStyle.UNDECORATED); // the title and close are eliminated
    Scene scene2 = new Scene(root2);
    stage2.setScene(scene2);

    //--- make possible to move the window draggin in any part of the stage---//
    root2.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
      }
    });
    root2.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        stage2.setX(event.getScreenX() - xOffset);
        stage2.setY(event.getScreenY() - yOffset);
      }
    });


    stage2.show();

    //-------------------Finished the Second View (Temperature)--------------------------------------------------//
  }
}
