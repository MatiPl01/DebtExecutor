package pl.age.edu.api.user;

import pl.age.edu.api.user.dto.SignInUserDTO;
import pl.age.edu.api.user.dto.SingUpUserDTO;
import pl.age.edu.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface UserService {
    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    Call<User> signUpUser(@Body SingUpUserDTO dto);

    @POST("users/login")
    Call<User> singInUser(@Body SignInUserDTO dto);
}
