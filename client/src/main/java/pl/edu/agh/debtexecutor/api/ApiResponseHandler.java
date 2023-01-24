package pl.edu.agh.debtexecutor.api;

import com.google.gson.Gson;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.utils.DialogUtils;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

@Component
public class ApiResponseHandler {
    private static final Gson gson = new Gson();

    public <R> Optional<R> handleResponse(Call<R> call) {
        try {
            Response<R> response = call.execute();
            if (!response.isSuccessful()) handleErrorResponse(response);
            return Optional.ofNullable(response.body());
        } catch (IOException e) {
            e.printStackTrace();
            DialogUtils.errorDialog(
                    "Error",
                    "Ooops! Something went wrong.\nTry again later."
            );
        }
        return Optional.empty();
    }

    private <R> void handleErrorResponse(Response<R> response)
            throws IOException {
        ResponseBody responseBody = response.errorBody();

        if (responseBody == null) {
            throw new IOException("No error response body");
        } else {
            ErrorBody errorBody = parseErrorBody(responseBody.string());
            DialogUtils.errorDialog(
                    errorBody.getError(),
                    errorBody.getMessage()
            );
        }
    }

    private ErrorBody parseErrorBody(String stringBody) {
        return gson.fromJson(stringBody, ErrorBody.class);
    }
}
