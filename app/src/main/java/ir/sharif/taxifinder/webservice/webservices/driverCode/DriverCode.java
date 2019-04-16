package ir.sharif.taxifinder.webservice.webservices.driverCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverCode {
    public String getDriverCode() {
        return driverCode;
    }

    @SerializedName("driverCode")
    @Expose
    private String driverCode;

}