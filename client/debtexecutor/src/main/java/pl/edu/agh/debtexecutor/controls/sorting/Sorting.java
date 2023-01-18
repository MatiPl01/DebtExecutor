package pl.edu.agh.debtexecutor.controls.sorting;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import pl.edu.agh.debtexecutor.services.options.SortDirection;
import pl.edu.agh.debtexecutor.services.utils.SortingService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sorting extends HBox {
    private static final String FXML_PATH = "/fxml/controls/Sorting.fxml";
    private final Map<String, String> propNamesMap = new HashMap<>();
    private final Map<String, SortValueType> valueTypesMap = new HashMap<>();
    private List<SortSettings> settings;
    private SortingService sortingService;

    @FXML private ComboBox<String> sortByCombobox;
    @FXML private ComboBox<String> sortDirectionCombobox;

    public Sorting() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSortingService(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    public void setSortSettings(List<SortSettings> settings) {
        this.settings = settings;
        createHelperMaps();
        initializeComboboxes();

        sortingService.sortBy(propNamesMap.get(sortByCombobox.getValue()));
        sortingService.setSortDirection(getSortDirection());
        addEventListeners();
    }

    private void createHelperMaps() {
        settings.forEach(setting -> propNamesMap.put(
                setting.sortByName(),
                setting.sortByProp())
        );
        settings.forEach(setting -> valueTypesMap.put(
                setting.sortByName(),
                setting.sortValueType())
        );
    }

    private void initializeComboboxes() {
        if (settings.isEmpty()) return;
        sortByCombobox.getItems().setAll(
                settings.stream().map(SortSettings::sortByName).toList()
        );
        sortByCombobox.setValue(settings.get(0).sortByName());
        updateSortDirectionCombobox();
    }

    private void updateSortDirectionCombobox() {
        int idx = sortDirectionCombobox.getItems().indexOf(sortDirectionCombobox.getValue());
        if (idx == -1) idx = 0;
        if (valueTypesMap.get(sortByCombobox.getValue()) ==
            SortValueType.LEXICAL) {
            sortDirectionCombobox.getItems().setAll("A-Z", "Z-A");
        } else {
            sortDirectionCombobox.getItems().setAll("DESC", "ASC");
        }
        sortDirectionCombobox.setValue(sortDirectionCombobox.getItems().get(idx));
    }

    private SortDirection getSortDirection() {
        int idx = sortDirectionCombobox.getItems().indexOf(sortDirectionCombobox.getValue());
        if (valueTypesMap.get(sortByCombobox.getValue()) ==
            SortValueType.LEXICAL) {
            return idx == 0 ? SortDirection.ASC : SortDirection.DESC;
        }
        return idx == 0 ? SortDirection.DESC : SortDirection.ASC;
    }

    private void addEventListeners() {
        sortByCombobox.setOnAction((v) -> {
            updateSortDirectionCombobox();
            sortingService.sortBy(propNamesMap.get(sortByCombobox.getValue()));
        });

        sortDirectionCombobox.setOnAction((v) -> {
            sortingService.setSortDirection(getSortDirection());
        });
    }
}
