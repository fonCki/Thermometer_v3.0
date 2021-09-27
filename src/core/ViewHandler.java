package core;

import external.Temperature;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.TemperatureList;
import view.master.RadiatorDimmerController;
import view.thermometer.TemperatureViewController;

import java.io.IOException;

public class ViewHandler {


  private Stage stage;
  private ViewModelFactory viewModelFactory;

  private RadiatorDimmerController radiatorDimmerController;

  private TemperatureViewController temperatureViewController;

  private double xOffset = 0;
  private double yOffset = 0;

  public ViewHandler(ViewModelFactory viewModelFactory) {
    this.viewModelFactory = viewModelFactory;
    this.stage = stage;
  }

  public void start() throws IOException {
    Stage stage = new Stage();
    Stage stage2 = new Stage();
    Stage stage3 = new Stage();

    Stage radiatorControlStage = new Stage();

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

    //-----------------------------------------------------------------------------------//

    stage.show();


    /////--------- FINISH THE FIRSt view


    Scene scene2, scene3;
    Parent root2, root3;

    FXMLLoader loader2 = new FXMLLoader();
    loader2.setLocation(getClass().getResource("../view/thermometer/TemperatureView.fxml"));
    root2 = loader2.load();

    TemperatureViewController ctrl2 = loader2.getController();
    temperatureViewController = ctrl2;
    temperatureViewController.init(viewModelFactory.getTemperatureViewModel());


    //---------------------------------------------------////

    stage2.initStyle(StageStyle.UNDECORATED); // the title and close are eliminated


    scene2 = new Scene(root2);
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







    /*
    Scene scene;
    Parent root;

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../view/master/RadiatorDimmer.fxml"));
    root = loader.load();

    ViewController ctrl = loader.getController();
    currentlyActive = ctrl;
    ctrl.init(this, viewModelFactory);

    stage.setTitle("Temperature");
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

     */
///////////// END STAGE 2//////////////////////////////////////////


    //////////////////// STAGE 3--------------------//////////////////

    stage3.setTitle("Line Chart Sample");
    //defining the axes
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Number of Month");
    //creating the chart
    final LineChart<Number, Number> lineChart =
            new LineChart<Number, Number>(xAxis, yAxis);

    lineChart.setTitle("Stock Monitoring, 2010");
    //defining a series
    XYChart.Series series = new XYChart.Series();
    series.setName("My portfolio");
    //populating the series with data
    series.getData().add(new XYChart.Data(1, 23));
    series.getData().add(new XYChart.Data(2, 14));
    series.getData().add(new XYChart.Data(3, 15));
    series.getData().add(new XYChart.Data(4, 24));
    series.getData().add(new XYChart.Data(5, 34));
    series.getData().add(new XYChart.Data(6, 36));
    series.getData().add(new XYChart.Data(7, 22));
    series.getData().add(new XYChart.Data(8, 45));
    series.getData().add(new XYChart.Data(9, 43));
    series.getData().add(new XYChart.Data(10, 17));
    series.getData().add(new XYChart.Data(11, 29));
    series.getData().add(new XYChart.Data(12, 25));

    scene3 = new Scene(lineChart, 800, 600);
    lineChart.getData().add(series);

    stage3.setScene(scene3);
    stage3.show();


  }


  public Parent openTemperatureView() {
    //currentlyActive.reset();
    return getRoot("../view/thermometer/TemperatureView.fxml");
  }


  public Parent openGraphsView() {
  //  currentlyActive.reset();
    return getRoot("../view/graphs/GraphsView.fxml");
  }


  private Parent getRoot(String s) {
    Parent root = null;
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(s));
      root = loader.load();
      //ViewController ctrl = loader.getController();
    //  currentlyActive = ctrl;
    //  ctrl.init(this, viewModelFactory);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return root;




  }
}
