package ir.sharif.taxifinder.webservice.base

import ir.sharif.taxifinder.webservice.webservices.driverDetail.DriverDetailResponse
import ir.sharif.taxifinder.webservice.webservices.driverDetail.DriverDetailRequest
import ir.sharif.taxifinder.webservice.webservices.driverRegister.DriverRegisterRequest
import ir.sharif.taxifinder.webservice.webservices.driverRegister.DriverRegisterResponse
import ir.sharif.taxifinder.webservice.webservices.drivers.DriversRequest
import ir.sharif.taxifinder.webservice.webservices.drivers.DriversResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebserviceUrls {

    @GET(WebserviceAddresses.DRIVERS)
    fun drivers(): Call<DriversResponse>


    @GET(WebserviceAddresses.DRIVER_DETAIL)
    fun driverDetail(@Body request: DriverDetailRequest): Call<DriverDetailResponse>


    @POST(WebserviceAddresses.DRIVER_REGISTER)
    fun driverRegister(@Body request: DriverRegisterRequest): Call<DriverRegisterResponse>

}
