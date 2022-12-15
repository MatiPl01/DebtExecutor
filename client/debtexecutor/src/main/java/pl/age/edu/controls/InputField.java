package pl.age.edu.controls;

import javafx.animation.*;
import javafx.beans.NamedArg;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InputField extends StackPane implements Initializable {
    private static final String FXML_PATH = "/fxml/controls/InputField.fxml";
    private static final Duration TRANSITION_DURATION = Duration.millis(300);

    @FXML
    private Pane underline;

    @FXML
    private StackPane labelWrapper;

    @FXML
    private Label labelBlur;

    @FXML
    private Label labelFocus;

    @FXML
    private TextField input;

    private final String labelText;
    private Transition labelFocusTransition;
    private Transition labelBlurTransition;
    private Transition underlineFocusTransition;
    private Transition underlineBlurTransition;

    public InputField(@NamedArg("label") String labelText) {
        this.labelText = labelText;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateLabel();
        createLabelFocusTransition();
        createLabelBlurTransition();
        createUnderlineFocusTransition();
        createUnderlineBlurTransition();
        addEventListeners();
    }

    public String getText() {
        return input.getText();
    }

    public void setText(String text) {
        textProperty().set(text);
    }

    public StringProperty textProperty() {
        return input.textProperty();
    }

    private void updateLabel() {
        labelFocus.setText(labelText);
        labelBlur.setText(labelText);
    }

    private void createLabelFocusTransition() {
        // Translate transition
        TranslateTransition translateTransition = new TranslateTransition(TRANSITION_DURATION, labelWrapper);
        translateTransition.setByY(-30);
        translateTransition.setByX(-10);

        // Scale transition
        ScaleTransition scaleTransition = new ScaleTransition(TRANSITION_DURATION, labelWrapper);
        scaleTransition.setToX(.8);
        scaleTransition.setToY(.8);

        // Font color transition
        FadeTransition fadeTransition = new FadeTransition(TRANSITION_DURATION, labelFocus);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        labelFocusTransition = new ParallelTransition(
                translateTransition,
                scaleTransition,
                fadeTransition
        );
    }

    private void createLabelBlurTransition() {
        // Translate transition
        TranslateTransition translateTransition = new TranslateTransition(TRANSITION_DURATION, labelWrapper);
        translateTransition.setByY(30);
        translateTransition.setByX(10);

        // Scale transition
        ScaleTransition scaleTransition = new ScaleTransition(TRANSITION_DURATION, labelWrapper);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);

        // Font color transition
        FadeTransition fadeTransition = new FadeTransition(TRANSITION_DURATION, labelFocus);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        labelBlurTransition = new ParallelTransition(
                translateTransition,
                scaleTransition,
                fadeTransition
        );
    }

    private void createUnderlineFocusTransition() {
        // Scale transition
        ScaleTransition scaleTransition = new ScaleTransition(TRANSITION_DURATION, underline);
        scaleTransition.setToX(1);
        underlineFocusTransition = scaleTransition;
    }

    private void createUnderlineBlurTransition() {
        // Scale transition
        ScaleTransition scaleTransition = new ScaleTransition(TRANSITION_DURATION, underline);
        scaleTransition.setToX(0);
        underlineBlurTransition = scaleTransition;
    }

    private void addEventListeners() {
        input.focusedProperty().addListener(((observable, wasFocused, isFocused) -> {
            // Do not trigger label animation if the input field is not empty
            if (!textProperty().get().isEmpty()) return;

            if (isFocused) {
                labelFocusTransition.play();
                underlineFocusTransition.play();
            } else {
                labelBlurTransition.play();
                underlineBlurTransition.play();
            }
        }));
    }
}
