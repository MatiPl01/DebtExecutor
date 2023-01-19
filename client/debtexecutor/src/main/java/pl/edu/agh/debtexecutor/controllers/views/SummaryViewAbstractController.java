package pl.edu.agh.debtexecutor.controllers.views;

import pl.edu.agh.debtexecutor.controllers.core.ViewType;

public abstract class SummaryViewAbstractController extends SwitchableViewController {
    @Override
    protected void onListViewSwitch() {
        viewController.switchView(ViewType.SUMMARY);
    }

    @Override
    protected void onGraphViewSwitch() {
        viewController.switchView(ViewType.SUMMARY_GRAPH);
    }
}
