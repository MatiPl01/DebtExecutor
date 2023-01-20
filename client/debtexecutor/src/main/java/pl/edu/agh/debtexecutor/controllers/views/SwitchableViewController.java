package pl.edu.agh.debtexecutor.controllers.views;

import javafx.scene.layout.HBox;
import pl.edu.agh.debtexecutor.controllers.core.ViewController;
import pl.edu.agh.debtexecutor.controllers.core.ViewType;
import pl.edu.agh.debtexecutor.controls.ViewSwitch;

public abstract class SwitchableViewController {
    protected static ViewSwitch viewSwitch = new ViewSwitch();
    protected ViewController viewController;
    protected HBox headerContent;

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
        ViewType prevView = viewController.getActiveView();
        headerContent = viewController.getHeaderContent();
        headerContent.getChildren().setAll(viewSwitch);
        viewSwitch.setOnListViewClick(this::onListViewSwitch);
        viewSwitch.setOnGraphViewClick(this::onGraphViewSwitch);
        updateViewSwitch(prevView);
    }

    abstract void onListViewSwitch();

    abstract void onGraphViewSwitch();

    private void updateViewSwitch(ViewType prevView) {
        if (prevView == ViewType.HISTORY_GRAPH || prevView == ViewType.SUMMARY_GRAPH) {
            viewSwitch.reset();
        }
    }
}
