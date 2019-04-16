package ir.sharif.taxifinder.webservice.webservices.driverRegister

import com.google.gson.annotations.SerializedName
import ir.sharif.taxifinder.webservice.base.BaseRequest

class DriverRegisterRequest(@SerializedName("uuid") val uuid: String,
                            @SerializedName("driverId") val driverId: Int) : BaseRequest()
