package pl.age.edu.api;


import pl.age.edu.models.User;

import java.util.List;

import pl.age.edu.utils.CreateUserDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @GET("api/v1/users")
    Call<List<User>> getUsers();

    @POST("api/v1/users")
    Call<Void>  addUser(@Body CreateUserDTO user);

}
