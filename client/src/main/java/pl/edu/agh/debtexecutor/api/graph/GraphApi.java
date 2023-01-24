package pl.edu.agh.debtexecutor.api.graph;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.ApiResponseHandler;
import pl.edu.agh.debtexecutor.api.RetrofitClient;
import pl.edu.agh.debtexecutor.models.graph.GraphModel;
import retrofit2.Call;

import java.util.Optional;

@Component
public class GraphApi {
    private final GraphApiService graphApiService;
    private final ApiResponseHandler handler;

    public GraphApi(RetrofitClient retrofitClient, ApiResponseHandler handler) {
        this.handler = handler;
        graphApiService = retrofitClient.getClient().create(GraphApiService.class);
    }

    public Optional<GraphModel> getExpenseHistoryGraph() {
        return getExpenseGraph(graphApiService.getExpenseHistoryGraph());
    }

    public Optional<GraphModel> getExpenseSummaryGraph() {
        return getExpenseGraph(graphApiService.getExpenseSummaryGraph());
    }

    public Optional<GraphModel> getSimplifiedExpenseGraph() {
        return getExpenseGraph(graphApiService.getSimplifiedExpenseGraph());
    }

    private Optional<GraphModel> getExpenseGraph(Call<GraphModel> response) {
        return handler.handleResponse(response);
    }
}
