package ir.sharif.taxifinder.webservice.webservices.driverDetail

import com.google.gson.annotations.SerializedName
import ir.sharif.taxifinder.webservice.base.BaseRequest

class DriverDetailRequest(@SerializedName("plate") val plateNo: String, @SerializedName("id") val id: String) : BaseRequest()
