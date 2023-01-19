package pl.edu.agh.debtexecutor.api.graph.dto;

public record GraphEdgeDTO(
        String vertex1,
        String vertex2,
        String label
) {}
