package pl.age.edu.controllers.core;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pl.age.edu.utils.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public class ViewController {
    private final static ViewType INITIAL_VIEW = ViewType.HISTORY;

    private final Pane wrapper;
    private final Label label;
    private final Map<ViewType, Pane> views = new HashMap<>();
    private ViewType activeView;

    public ViewController(Pane wrapper, Label label) {
        this.wrapper = wrapper;
        this.label = label;
        loadViews();
        switchView(INITIAL_VIEW);
    }

    public ViewType getActiveView() {
        return activeView;
    }

    public void switchView(ViewType newView) {
        wrapper.getChildren().clear();
        wrapper.getChildren().add(views.get(newView));
        label.setText(newView.toString());
        activeView = newView;
    }

    private void loadViews() {
        loadView(ViewType.CREATE_EXPENSE, "/fxml/views/CreateExpenseView.fxml");
        loadView(ViewType.CREATE_GROUP, "/fxml/views/CreateGroupView.fxml");
        loadView(ViewType.HISTORY, "/fxml/views/HistoryView.fxml");
        loadView(ViewType.SUMMARY, "/fxml/views/SummaryView.fxml");
    }

    private void loadView(ViewType viewType, String resourcePath) {
        views.put(viewType, ResourceLoader.loadFXML(resourcePath));
    }
}
