package pl.edu.agh.debtexecutor.api.graph;

import pl.edu.agh.debtexecutor.models.graph.GraphModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GraphApiService {
    @GET("graphs/history")
    Call<GraphModel> getExpenseHistoryGraph();

    @GET("graphs/summary")
    Call<GraphModel> getExpenseSummaryGraph();

    @GET("graphs/simplified")
    Call<GraphModel> getSimplifiedExpenseGraph();
}
