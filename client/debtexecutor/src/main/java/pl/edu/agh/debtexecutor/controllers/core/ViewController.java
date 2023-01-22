package pl.edu.agh.debtexecutor.controllers.core;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import pl.edu.agh.debtexecutor.controllers.views.tabs.SwitchableViewController;
import pl.edu.agh.debtexecutor.utils.FXMLWithController;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public class ViewController {
    private final static ViewType INITIAL_VIEW = ViewType.HISTORY;

    private final Pane wrapper;
    private final Label label;
    private final HBox headerContent;
    private final Map<ViewType, String> views = new HashMap<>() {{
        put(ViewType.CREATE_EXPENSE, "/fxml/views/CreatePersonalExpenseView.fxml");
        put(ViewType.CREATE_GROUP_EXPENSE, "/fxml/views/CreateGroupExpenseView.fxml");
        put(ViewType.CREATE_CATEGORY, "/fxml/views/CreateCategoryView.fxml");
        put(ViewType.CREATE_GROUP, "/fxml/views/CreateGroupView.fxml");
        put(ViewType.HISTORY, "/fxml/views/HistoryView.fxml");
        put(ViewType.SUMMARY, "/fxml/views/SummaryView.fxml");
        put(ViewType.HISTORY_GRAPH, "/fxml/views/HistoryGraphView.fxml");
        put(ViewType.SUMMARY_GRAPH, "/fxml/views/SummaryGraphView.fxml");
        put(ViewType.SIMPLIFIED_GRAPH, "/fxml/views/SimplifiedGraphView.fxml");
    }};
    private ViewType activeView;

    public ViewController(Pane wrapper, Label label, HBox headerContent) {
        this.wrapper = wrapper;
        this.label = label;
        this.headerContent = headerContent;
        switchView(INITIAL_VIEW);
    }

    public ViewType getActiveView() {
        return activeView;
    }

    public HBox getHeaderContent() {
        return headerContent;
    }

    public void switchView(ViewType newView) {
        headerContent.getChildren().clear();
        wrapper.getChildren().clear();
        Pane pane = loadView(newView);
        wrapper.getChildren().add(pane);
        label.setText(newView.toString());
        activeView = newView;
    }

    private Pane loadView(ViewType viewType) {
        return switch (viewType) {
            case HISTORY, SUMMARY, HISTORY_GRAPH, SUMMARY_GRAPH ->
                    loadWithController(viewType);
            default -> ResourceLoader.loadFXML(views.get(viewType));
        };
    }

    private <C extends SwitchableViewController> Pane loadWithController(
            ViewType viewType) {
        FXMLWithController<Pane, C> result =
                ResourceLoader.loadFXMLWithController(views.get(viewType));
        result.controller().setViewController(this);
        return result.node();
    }
}
