package pl.age.edu.api.user;


import pl.age.edu.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface UserService {
    @GET("api/v1/users")
    Call<List<User>> getUsers();

    @POST("api/v1/users")
    Call<Void>  addUser(@Body CreateUserDTO user);

}
