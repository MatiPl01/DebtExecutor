package pl.edu.agh.debtexecutor.controllers.views.tabs;

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

    protected abstract void onListViewSwitch();

    protected abstract void onGraphViewSwitch();

    private void updateViewSwitch(ViewType prevView) {
        if (prevView != ViewType.SUMMARY && prevView != ViewType.HISTORY) {
            viewSwitch.reset();
        }
    }
}
