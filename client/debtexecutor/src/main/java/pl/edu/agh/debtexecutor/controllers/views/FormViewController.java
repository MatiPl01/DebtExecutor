package pl.edu.agh.debtexecutor.controllers.views;

import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.List;

public abstract class FormViewController {
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
    }
}
