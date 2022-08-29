package view.master;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class RadiatorDimmerController {
    private RadiatorViewModel radiatorViewModel;
    @FXML private Label labelRadiator;

    public void init(RadiatorViewModel radiatorViewModel) {
        this.radiatorViewModel = radiatorViewModel;
        labelRadiator.textProperty().bind(radiatorViewModel.radiatorProperty().asString());
    }

    public void onUpButton(MouseEvent actionEvent) {
        radiatorViewModel.upRadiator();
    }

    public void onDownButton(MouseEvent actionEvent) {
        radiatorViewModel.downRadiator();
    }


}
