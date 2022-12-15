package pl.age.edu.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SummaryItem extends VBox {
    private static final String FXML_PATH = "/fxml/controls/SummaryItem.fxml";

    public SummaryItem() {
        // TODO - improve FXML loading

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
