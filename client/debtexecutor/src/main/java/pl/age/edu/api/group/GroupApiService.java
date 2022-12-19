package pl.age.edu.api.group;

import pl.age.edu.api.group.dto.CreateGroupDTO;
import pl.age.edu.models.Group;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface GroupApiService {
    @GET("groups")
    Call<List<Group>> getGroups();

    @POST("groups")
    Call<Void> addGroup(@Body CreateGroupDTO user);
}
