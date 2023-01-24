package pl.edu.agh.debtexecutor.models.graph;

public final class Vertex {
    private final String userName;
    private final String login;

    public Vertex(String userName, String login) {
        this.userName = userName;
        this.login = login;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", userName, login);
    }

    public String getUserLogin() {
        return login;
    }

    public String getUserName() {
        return userName;
    }
}
