package pl.age.edu.api.group;

import pl.age.edu.api.group.GroupController;
import pl.age.edu.models.Group;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface GroupService {
    @GET("api/v1/groups")
    Call<List<Group>> getGroups();

    @POST("api/v1/groups")
    Call<Void> addGroup(@Body CreateGroupDTO user);
}
