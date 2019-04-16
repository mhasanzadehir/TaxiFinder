package ir.sharif.taxifinder.webservice.webservices.driverCode

import com.google.gson.annotations.SerializedName
import ir.sharif.taxifinder.webservice.base.BaseRequest

class DriverCodeRequest(@SerializedName("uuid") val uuid: String) : BaseRequest()
