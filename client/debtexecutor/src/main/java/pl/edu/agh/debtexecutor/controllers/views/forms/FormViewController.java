package pl.edu.agh.debtexecutor.controllers.views.forms;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.List;

public abstract class FormViewController {
    protected static final double LIST_ROW_HEIGHT = 23;
    protected static final double MAX_LIST_HEIGHT = 141;

    @FXML
    private void onSubmit() {
        if (!validateForm()) return;
        if (!showConfirmationModal()) return;
        if (!submitForm()) return;
        handleSubmitSuccess();
        clearInputs();
    }

    protected <T> void setListEntries(ListView<T> listView, List<T> entries, SelectionMode selectionMode) {
        listView.setItems(FXCollections.observableArrayList(entries));
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(T entry, boolean empty) {
                super.updateItem(entry, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(entry.toString());
                }
            }
        });
        listView.getSelectionModel().setSelectionMode(selectionMode);
        listView.setPrefHeight(Math.min(MAX_LIST_HEIGHT, 2 + LIST_ROW_HEIGHT * entries.size()));
    }

    protected abstract boolean validateForm();
    protected abstract boolean showConfirmationModal();
    protected abstract boolean submitForm();
    protected abstract void clearInputs();
    protected abstract void handleSubmitSuccess();
}
