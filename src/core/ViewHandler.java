package core;

import core.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ViewController;

import java.io.IOException;

public class ViewHandler {


  private Stage stage;
  private ViewModelFactory viewModelFactory;

  private ViewController currentlyActive;

  public ViewHandler(ViewModelFactory viewModelFactory, Stage stage) {
    this.viewModelFactory = viewModelFactory;
    this.stage = stage;
  }

  public void start() throws IOException {
    Scene scene;
    Parent root;

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/master/MasterView.fxml"));
    root = loader.load();

    ViewController ctrl = loader.getController();
    currentlyActive = ctrl;
    ctrl.init(this, viewModelFactory);

    stage.setTitle("Temperature");
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }


  public Parent openTemperatureView() {
    currentlyActive.reset();
    return getRoot("../view/thermometer/TemperatureView.fxml");
  }

  public Parent openGraphsView() {
    currentlyActive.reset();
    return getRoot("../view/graphs/GraphsView.fxml");
  }

  private Parent getRoot(String s) {
    Parent root = null;
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(s));
      root = loader.load();
      ViewController ctrl = loader.getController();
      currentlyActive = ctrl;
      ctrl.init(this, viewModelFactory);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return root;
  }

}
