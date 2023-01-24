package pl.edu.agh.debtexecutor.controllers.views.tabs.expenseHistory;

import pl.edu.agh.debtexecutor.controllers.core.ViewType;
import pl.edu.agh.debtexecutor.controllers.views.tabs.SwitchableViewController;

public abstract class HistoryViewAbstractController extends
                                                    SwitchableViewController {
    @Override
    protected void onListViewSwitch() {
        viewController.switchView(ViewType.HISTORY);
    }

    @Override
    protected void onGraphViewSwitch() {
        viewController.switchView(ViewType.HISTORY_GRAPH);
    }
}
