package pl.edu.agh.debtexecutor.api.user;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.RetrofitClient;
import pl.edu.agh.debtexecutor.api.user.dto.SignInUserDTO;
import pl.edu.agh.debtexecutor.api.user.dto.SingUpUserDTO;
import pl.edu.agh.debtexecutor.models.User;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserApi {
    private final  UserApiService userService;

    public UserApi(RetrofitClient retrofitClient) {
        userService = retrofitClient.getClient().create(UserApiService.class);
    }

    public List<User> getAll() {
        try {
            List<User> users = userService.getUsers().execute().body();
            if (users != null) return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Optional<User> signIn(SignInUserDTO dto) {
        try {
            return Optional.ofNullable(userService.singInUser(dto).execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<User> singUp(SingUpUserDTO dto) {
        try {
            return Optional.ofNullable(userService.signUpUser(dto).execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
