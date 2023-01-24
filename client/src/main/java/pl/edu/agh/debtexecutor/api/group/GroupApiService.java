package pl.edu.agh.debtexecutor.api.group;

import pl.edu.agh.debtexecutor.api.group.dto.CreateGroupDTO;
import pl.edu.agh.debtexecutor.models.Group;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface GroupApiService {
    @GET("groups")
    Call<List<Group>> getGroups();

    @POST("groups")
    Call<Group> createGroup(@Body CreateGroupDTO user);
}
