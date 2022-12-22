package pl.edu.agh.debtexecutor.services;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.user.UserApi;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.utils.Interval;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private static final int FETCH_INTERVAL = 10000; // 10s

    private final ObservableList<User> users =
            FXCollections.observableArrayList();
    private final UserApi userApi;
    private final AuthService authService;
    private final Interval reFetchInterval;

    private UserService(UserApi userAPi, AuthService authService) {
        this.userApi = userAPi;
        this.authService = authService;
        reFetchInterval = new Interval(this::reFetch, FETCH_INTERVAL);
        reFetchInterval.start();
    }

    public void fetchData() {
        setUsers(userApi.getAll());
        reFetchInterval.reset();
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users.setAll(sortedUsers(users));
    }

    private List<User> sortedUsers(List<User> users) {
        return users.stream()
                    .sorted(Comparator.comparing(User::toString))
                    .toList();
    }

    private void reFetch() {
        Platform.runLater(() -> {
            setUsers(userApi.getAll());

            // Update the logged in user data
            Optional<User> loggedInUser = authService.getLoggedInUser();
            if (loggedInUser.isEmpty()) return;
            Optional<User> updatedUser = users
                    .stream()
                    .filter(user -> user.getId().equals(loggedInUser.get().getId()))
                    .findFirst();
            updatedUser.ifPresent(authService::setLoggedInUser);
        });
    }
}
