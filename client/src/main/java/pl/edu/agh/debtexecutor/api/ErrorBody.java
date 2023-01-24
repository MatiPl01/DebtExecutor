package pl.edu.agh.debtexecutor.api;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class ErrorBody {
    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("status")
    private int status;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    public LocalDateTime getTimestamp() {
        return LocalDateTime.parse(timestamp);
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
