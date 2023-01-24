package pl.edu.agh.debtexecutor.graphs.model;

import pl.edu.agh.debtexecutor.users.model.User;

import java.util.Objects;

public class Vertex {
    private final User user;

    public Vertex(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return user.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(user, vertex.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    public String getUserLogin() {
        return user.getLogin();
    }

    public String getUserName() {
        return user.getFirstName() + " " + user.getLastName();
    }
}
