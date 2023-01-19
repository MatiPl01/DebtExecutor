package pl.edu.agh.debtexecutor.controls.multiSelect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class MultiSelectCombobox extends MenuButton implements Initializable {
    private static final String FXML_PATH =
            "/fxml/controls/MultiSelectCombobox.fxml";
    private final static String SELECTED_CLASS = "selected";
    private final ObservableList<String> selectedValues = FXCollections.observableArrayList();
    private final Map<String, String> itemValuesMap = new HashMap<>();
    private Runnable clearHandler;

    @FXML
    private MenuButton menuButton;
    @FXML
    private Button clearButton;

    public MultiSelectCombobox() {
        ResourceLoader.loadControlFXML(FXML_PATH, this);
    }

    public ObservableList<String> getSelectedValues() {
        return selectedValues;
    }

    public void setItems(List<MultiselectItem> items) {
        createMenuItemsMap(items);
        menuButton.getItems()
                  .addAll(items.stream().map(this::createMenuItem).toList());
    }

    public void onClear(Runnable clearHandler) {
        this.clearHandler = clearHandler;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearButton.setOnAction((e) -> {
            menuButton.getItems().forEach(item -> {
                HBox wrapper = (HBox) ((CustomMenuItem) item).getContent();
                wrapper.getStyleClass().remove(SELECTED_CLASS);
            });
            selectedValues.clear();
            if (clearHandler != null) clearHandler.run();
        });
    }

    private void createMenuItemsMap(List<MultiselectItem> items) {
        items.forEach(item -> itemValuesMap.put(item.displayedValue(), item.value()));
    }

    private CustomMenuItem createMenuItem(MultiselectItem item) {
        Label label = new Label(item.displayedValue());
        HBox wrapper = new HBox();
        wrapper.setAlignment(Pos.CENTER_LEFT);
        CustomMenuItem menuItem = new CustomMenuItem(wrapper);

        wrapper.getStyleClass().add("multiselect__item");
        menuItem.getStyleClass().add("multiselect__item-wrapper");
        label.getStyleClass().add("multiselect__item-label");
        wrapper.getChildren().add(label);

        menuItem.setHideOnClick(false);
        addClickHandler(menuItem);

        return menuItem;
    }

    private void addClickHandler(CustomMenuItem item) {
        item.setOnAction(a -> {
            HBox wrapper = ((HBox) item.getContent());
            Label label = (Label) wrapper.getChildren().get(0);
            String displayedValue = label.getText();
            String value = itemValuesMap.get(displayedValue);
            List<String> classes = wrapper.getStyleClass();

            if (classes.contains(SELECTED_CLASS)) {
                selectedValues.remove(value);
                classes.remove(SELECTED_CLASS);
            } else {
                selectedValues.add(value);
                classes.add(SELECTED_CLASS);
            }
        });
    }
}
