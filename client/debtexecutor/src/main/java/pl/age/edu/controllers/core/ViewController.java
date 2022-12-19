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
    private final Map<ViewType, String> views = new HashMap<>();
    private ViewType activeView;

    public ViewController(Pane wrapper, Label label) {
        this.wrapper = wrapper;
        this.label = label;
        addViews();
        switchView(INITIAL_VIEW);
    }

    public ViewType getActiveView() {
        return activeView;
    }

    public void switchView(ViewType newView) {
        wrapper.getChildren().clear();
        Pane view = ResourceLoader.loadFXML(views.get(newView));
        wrapper.getChildren().add(view);
        label.setText(newView.toString());
        activeView = newView;
    }

    private void addViews() {
        views.put(ViewType.CREATE_EXPENSE, "/fxml/views/CreatePersonalExpenseView.fxml");
        views.put(ViewType.CREATE_GROUP_EXPENSE, "/fxml/views/CreateGroupExpenseView.fxml");
        views.put(ViewType.CREATE_GROUP, "/fxml/views/CreateGroupView.fxml");
        views.put(ViewType.HISTORY, "/fxml/views/HistoryView.fxml");
        views.put(ViewType.SUMMARY, "/fxml/views/SummaryView.fxml");
    }
}
