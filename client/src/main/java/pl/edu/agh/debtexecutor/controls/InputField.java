package pl.edu.agh.debtexecutor.controls;

import javafx.animation.*;
import javafx.beans.NamedArg;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import pl.edu.agh.debtexecutor.utils.NumberUtils;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class InputField extends StackPane implements Initializable {
    private static final String FXML_PATH = "/fxml/controls/InputField.fxml";

    private static final Duration TRANSITION_DURATION = Duration.millis(300);
    private static final String TYPE_TEXT = "text";
    private static final String TYPE_MONEY = "money";

    @FXML private Pane underline;
    @FXML private StackPane labelWrapper;
    @FXML private Label labelBlur;
    @FXML private Label labelFocus;
    @FXML private TextField input;

    private final String labelText;
    private final String inputType;
    private Transition labelFocusTransition;
    private Transition labelBlurTransition;
    private Transition underlineFocusTransition;
    private Transition underlineBlurTransition;

    public InputField(@NamedArg("label") String labelText, @NamedArg("type") String inputType) {
        this.labelText = labelText;
        this.inputType = inputType;
        ResourceLoader.loadControlFXML(FXML_PATH, this);
    }

    public InputField(@NamedArg("label") String labelText) {
        this(labelText, TYPE_TEXT);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateLabel();
        createLabelFocusTransition();
        createLabelBlurTransition();
        createUnderlineFocusTransition();
        createUnderlineBlurTransition();
        addEventListeners();
        setupMask();
    }

    public String getText() {
        return input.getText();
    }

    public void setText(String text) {
        textProperty().set(text);
    }

    public void clear() {
        setText("");
        underline.requestFocus();
        labelBlurTransition.play();
        underlineBlurTransition.play();
    }

    public StringProperty textProperty() {
        return input.textProperty();
    }

    private void updateLabel() {
        labelFocus.setText(labelText);
        labelBlur.setText(labelText);
    }

    private void createLabelFocusTransition() {
        labelFocusTransition = new ParallelTransition(
                translateByTransition(labelWrapper, -30, -10),
                scaleToTransition(labelWrapper, .8, .8),
                fadeFromToTransition(labelFocus, 0, 1)
        );
    }

    private void createLabelBlurTransition() {
        labelBlurTransition = new ParallelTransition(
                translateByTransition(labelWrapper, 30, 10),
                scaleToTransition(labelWrapper, 1, 1),
                fadeFromToTransition(labelFocus, 1, 0)
        );
    }

    private TranslateTransition translateByTransition(Node node, int x, int y) {
        TranslateTransition translateTransition = new TranslateTransition(TRANSITION_DURATION, node);
        translateTransition.setByY(x);
        translateTransition.setByX(y);
        return translateTransition;
    }

    private ScaleTransition scaleToTransition(Node node, double x, double y) {
        ScaleTransition scaleTransition = new ScaleTransition(TRANSITION_DURATION, node);
        scaleTransition.setToX(x);
        scaleTransition.setToY(y);
        return scaleTransition;
    }

    private FadeTransition fadeFromToTransition(Node node, int from, int to) {
        FadeTransition fadeTransition = new FadeTransition(TRANSITION_DURATION, node);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        return fadeTransition;
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

    private void setupMask() {
        if (inputType.equals(TYPE_MONEY)) {
            input.textProperty().addListener(
                (observable, oldValue, newValue) -> maskMoney(oldValue, newValue)
            );
        }
    }

    private void maskMoney(String oldValue, String newValue) {
        if (newValue.isEmpty()) return;
        if (NumberUtils.isPositive(newValue)) {
            String[] parts = newValue.split("\\.");
            if (parts.length < 2 || parts[1].length() <= 2) return;
        }
        input.textProperty().set(oldValue);
    }
}
