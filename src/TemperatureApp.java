import core.ModelFactory;
import core.ViewModelFactory;
import external.ExternalTemperature;
import external.Thermometer;
import javafx.application.Application;
import javafx.stage.Stage;
import core.ViewHandler;

public class TemperatureApp extends Application {

  public void start(Stage primaryStage) throws Exception{

    //Initializing core///
    ModelFactory modelFactory = new ModelFactory();
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory, primaryStage);

    //Creating Threads///
    Runnable externalTemp = new ExternalTemperature(modelFactory.getTemperatureModel(), 0, -10, 28);

    Runnable thermometer1 = new Thermometer(modelFactory.getTemperatureModel(),"t1", 15,1);
    Runnable thermometer2 = new Thermometer(modelFactory.getTemperatureModel(),"t2", 15,7);

    Thread externalThread = new Thread(externalTemp);

    Thread t1 = new Thread(thermometer1);
    Thread t2 = new Thread(thermometer2);

    // Running Handler///
    viewHandler.start();

    //Running Threads//
    externalThread.setDaemon(true);
    t1.setDaemon(true);
    t2.setDaemon(true);

    externalThread.start();
    t1.start();
    t2.start();

  }
}