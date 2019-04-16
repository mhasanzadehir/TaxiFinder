package ir.sharif.taxifinder.webservice.webservices.driverDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DriverDetail implements Serializable {

    @SerializedName("driverId")
    @Expose
    private Integer driverId;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("firstname")
    @Expose
    private String firstName;
    @SerializedName("lastname")
    @Expose
    private String lastName;
    @SerializedName("msisdn")
    @Expose
    private String msisdn;
    @SerializedName("car")
    @Expose
    private Car car;

    public DriverDetail(Integer driverId, String imageUrl, String firstName, String lastName, String msisdn, Car car) {
        this.driverId = driverId;
        this.imageUrl = imageUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.msisdn = msisdn;
        this.car = car;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

}