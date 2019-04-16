package ir.sharif.taxifinder.webservice.webservices.driverCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverCodeResponse {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private DriverCode driverCode;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DriverCode getDriverCode() {
        return driverCode;
    }
}