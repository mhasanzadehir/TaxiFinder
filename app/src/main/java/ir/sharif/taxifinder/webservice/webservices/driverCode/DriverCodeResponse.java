package ir.sharif.taxifinder.webservice.webservices.driverCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverCodeResponse {
@SerializedName("driverCode")
    @Expose
    private String driverCode;


    public String getDriverCode() {
        return driverCode;
    }
}