package pl.edu.agh.debtexecutor.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.util.Optional;

public class DialogUtils {
    private static final String DIALOG_CLASS_NAME = "dialog";
    private static final String INFORMATION_DIALOG_CSS = ResourceLoader.loadCSS(
            "/css/dialogs/informationDialog.css"
    );
    private static final String CONFIRMATION_DIALOG_CSS = ResourceLoader.loadCSS(
            "/css/dialogs/confirmationDialog.css"
    );
    private static final String ERROR_DIALOG_CSS = ResourceLoader.loadCSS(
            "/css/dialogs/errorDialog.css"
    );

    public static void informationDialog(String title, String header, String content) {
        showInformationDialog(title, header, content);
    }

    public static void informationDialog(String header, String content) {
        showInformationDialog("Information", header, content);
    }

    private static void showInformationDialog(String title, String header, String content) {
        Alert informationDialog = new Alert(Alert.AlertType.INFORMATION);
        informationDialog.setTitle(title);
        informationDialog.setHeaderText(header);
        informationDialog.setContentText(content);
        informationDialog.showAndWait();
        loadCSS(informationDialog, Alert.AlertType.INFORMATION);
    }

    public static Optional<ButtonType> confirmationDialog(String title, String header) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(header);
        loadCSS(confirmationDialog, Alert.AlertType.CONFIRMATION);
        return confirmationDialog.showAndWait();
    }

    public static void errorDialog(String title, String header) {
        Alert errorDialog = new Alert(Alert.AlertType.ERROR);
        errorDialog.setTitle(title);
        errorDialog.setHeaderText(header);
        errorDialog.showAndWait();
        loadCSS(errorDialog, Alert.AlertType.ERROR);
    }

    private static void loadCSS(Alert alert, Alert.AlertType dialogType) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStyleClass().add(DIALOG_CLASS_NAME);

        switch (dialogType) {
            case INFORMATION -> dialogPane.getStylesheets().add(INFORMATION_DIALOG_CSS);
            case CONFIRMATION -> dialogPane.getStylesheets().add(CONFIRMATION_DIALOG_CSS);
            case ERROR -> dialogPane.getStylesheets().add(ERROR_DIALOG_CSS);
        }
    }
}
