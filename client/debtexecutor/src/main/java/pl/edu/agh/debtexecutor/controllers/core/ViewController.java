package pl.edu.agh.debtexecutor.controllers.core;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public class ViewController {
    private final static ViewType INITIAL_VIEW = ViewType.HISTORY;

    private final Pane wrapper;
    private final Label label;
    private final Map<ViewType, String> views = new HashMap<>() {{
        put(ViewType.CREATE_EXPENSE, "/fxml/views/CreatePersonalExpenseView.fxml");
        put(ViewType.CREATE_GROUP_EXPENSE, "/fxml/views/CreateGroupExpenseView.fxml");
        put(ViewType.CREATE_CATEGORY, "/fxml/views/CreateCategoryView.fxml");
        put(ViewType.CREATE_GROUP, "/fxml/views/CreateGroupView.fxml");
        put(ViewType.HISTORY, "/fxml/views/HistoryView.fxml");
        put(ViewType.SUMMARY, "/fxml/views/SummaryView.fxml");
    }};
    private ViewType activeView;

    public ViewController(Pane wrapper, Label label) {
        this.wrapper = wrapper;
        this.label = label;
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
}
