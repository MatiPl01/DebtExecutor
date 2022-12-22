package pl.edu.agh.debtexecutor.api.user;

import pl.edu.agh.debtexecutor.api.user.dto.SignInUserDTO;
import pl.edu.agh.debtexecutor.api.user.dto.SingUpUserDTO;
import pl.edu.agh.debtexecutor.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface UserApiService {
    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    Call<User> signUpUser(@Body SingUpUserDTO dto);

    @POST("users/login")
    Call<User> singInUser(@Body SignInUserDTO dto);
}
