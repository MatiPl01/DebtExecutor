package pl.edu.agh.debtexecutor.api.user;

import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.api.ApiResponseHandler;
import pl.edu.agh.debtexecutor.api.RetrofitClient;
import pl.edu.agh.debtexecutor.api.user.dto.SignInUserDTO;
import pl.edu.agh.debtexecutor.api.user.dto.SingUpUserDTO;
import pl.edu.agh.debtexecutor.models.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserApi {
    private final UserApiService userApiService;
    private final ApiResponseHandler handler;

    public UserApi(RetrofitClient retrofitClient, ApiResponseHandler handler) {
        userApiService = retrofitClient.getClient().create(UserApiService.class);
        this.handler = handler;
    }

    public List<User> getAll() {
        Optional<List<User>> users = handler.handleResponse(userApiService.getUsers());
        return users.orElse(Collections.emptyList());
    }

    public Optional<User> signIn(SignInUserDTO dto) {
        return handler.handleResponse(userApiService.singInUser(dto));
    }

    public Optional<User> singUp(SingUpUserDTO dto) {
        return handler.handleResponse(userApiService.signUpUser(dto));
    }
}
