package pl.edu.agh.debtexecutor.graphs.model;

import pl.edu.agh.debtexecutor.users.model.User;

import java.math.BigDecimal;
import java.util.*;

public class BasicGraph implements Graph {
    private final Map<User, Vertex> userVertexMap = new HashMap<>();

    private final List<Edge> edges = new ArrayList<>();
    private final List<Vertex> vertices = new ArrayList<>();

    public void addVertex(User user) {
        Vertex vertex = new Vertex(user);
        userVertexMap.put(user, vertex);
        vertices.add(vertex);
    }

    public void addEdge(User payer, User payee, BigDecimal amount) {
        edges.add(new Edge(userVertexMap.get(payer), userVertexMap.get(payee), amount));
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }
}
