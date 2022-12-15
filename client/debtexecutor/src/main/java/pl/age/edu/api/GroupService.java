package pl.age.edu.api;

import pl.age.edu.models.Group;
import pl.age.edu.utils.CreateUserDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface GroupService {
    @GET("api/v1/groups")
    Call<List<Group>> getGroups();

    @POST("api/v1/groups")
    Call<Void>  addGroup(@Body CreateUserDTO user);
}
