package pl.edu.agh.debtexecutor.graphs.model;

import pl.edu.agh.debtexecutor.users.model.User;

public class Vertex {
    private final User user;

    public Vertex(User user) {
        this.user = user;
    }

    public String getUserLogin() {
        return user.getLogin();
    }

    public String getUserName() {
        return user.getFirstName() + " " + user.getLastName();
    }
}
