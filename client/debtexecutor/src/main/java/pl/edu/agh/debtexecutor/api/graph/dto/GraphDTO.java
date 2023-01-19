package pl.edu.agh.debtexecutor.api.graph.dto;

import java.util.List;

public record GraphDTO(
        List<GraphVertexDTO> vertices,
        List<GraphEdgeDTO> edges
) {}
