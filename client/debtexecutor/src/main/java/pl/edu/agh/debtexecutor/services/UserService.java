package pl.edu.agh.debtexecutor.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.user.UserApi;
import pl.edu.agh.debtexecutor.models.User;

import java.util.Comparator;
import java.util.List;

@Component
public class UserService {

    private final ObservableList<User> users =
            FXCollections.observableArrayList();

    private final UserApi userApi;

    private UserService(UserApi userAPi) {
        this.userApi = userAPi;
    }

    public void fetchData() {
        setUsers(userApi.getAll());
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users.setAll(sortedUsers(users));
    }

    private List<User> sortedUsers(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::toString)).toList();
    }
}
