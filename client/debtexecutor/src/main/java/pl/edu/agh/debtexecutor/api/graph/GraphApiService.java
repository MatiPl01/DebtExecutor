package pl.edu.agh.debtexecutor.api.graph;

import pl.edu.agh.debtexecutor.api.graph.dto.GraphEdgeDTO;
import pl.edu.agh.debtexecutor.api.graph.dto.GraphVertexDTO;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface GraphApiService {
    // TODO: Add endpoints. May work then.
    @GET("graph/edges")
    Call<List<GraphEdgeDTO>> getGraphEdges();

    @GET("graph/vertices")
    Call<List<GraphVertexDTO>> getGraphVertices();
}
