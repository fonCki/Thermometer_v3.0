import core.ModelFactory;
import core.ViewModelFactory;
import external.External;
import external.Temperature;
import external.Thermometer;
import javafx.application.Application;
import javafx.stage.Stage;
import core.ViewHandler;

import java.lang.management.MonitorInfo;
import java.security.PrivateKey;
import java.time.chrono.MinguoChronology;
import java.util.Random;

public class TemperatureApp extends Application {
  private final int MAX_OUTSIDE_TEMP = 32;
  private final int MIN_OUTSIDE_TEMP = -10;

  public void start(Stage primaryStage) throws Exception{


    //Initializing core///

    ModelFactory modelFactory = new ModelFactory();
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);


    // Running Handler///
    viewHandler.start();

    //Outside Temperature Thread///
    Temperature externalTemp = new External(modelFactory.getTemperatureModel(),
                                         new Random().nextInt(MAX_OUTSIDE_TEMP),
                                         MIN_OUTSIDE_TEMP,
                                         MAX_OUTSIDE_TEMP);
    Thread externalThread = new Thread(externalTemp);
    externalThread.setDaemon(true);
    externalThread.start();
    //---------------------------//



    //Thermometers Temperature Thread///
    Temperature thermometer1 = new Thermometer(modelFactory.getTemperatureModel(),
                                        "t1", modelFactory.getTemperatureModel().getOutsideTemp(),1);

    Temperature thermometer2 = new Thermometer(modelFactory.getTemperatureModel(),
                                        "t2", modelFactory.getTemperatureModel().getOutsideTemp(),7);

    Thread t1 = new Thread(thermometer1);
    Thread t2 = new Thread(thermometer2);
    t1.setDaemon(true);
    t2.setDaemon(true);
    t1.start();
    t2.start();
    //---------------------------//


  }
}