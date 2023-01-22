package pl.edu.agh.debtexecutor.controllers.views.tabs.expenseSummary;

import pl.edu.agh.debtexecutor.controllers.core.ViewType;
import pl.edu.agh.debtexecutor.controllers.views.tabs.SwitchableViewController;

public abstract class SummaryViewAbstractController extends
                                                    SwitchableViewController {
    @Override
    protected void onListViewSwitch() {
        viewController.switchView(ViewType.SUMMARY);
    }

    @Override
    protected void onGraphViewSwitch() {
        viewController.switchView(ViewType.SUMMARY_GRAPH);
    }
}
