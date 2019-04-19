package ir.sharif.taxifinder.webservice

import ir.sharif.taxifinder.webservice.base.WebserviceException
import ir.sharif.taxifinder.webservice.webservices.driverCode.DriverCodeProcess
import ir.sharif.taxifinder.webservice.webservices.driverCode.DriverCodeResponse
import ir.sharif.taxifinder.webservice.webservices.driverDetail.DriverDetailProcess
import ir.sharif.taxifinder.webservice.webservices.driverDetail.DriverDetailResponse
import ir.sharif.taxifinder.webservice.webservices.driverRegister.DriverRegisterProcess
import ir.sharif.taxifinder.webservice.webservices.driverRegister.DriverRegisterResponse
import ir.sharif.taxifinder.webservice.webservices.drivers.DriversProcess
import ir.sharif.taxifinder.webservice.webservices.drivers.DriversResponse
import ir.sharif.taxifinder.webservice.webservices.login.LoginProcess
import ir.sharif.taxifinder.webservice.webservices.login.LoginResponse
import java.io.IOException

object WebserviceHelper {

    @Throws(IOException::class, WebserviceException::class)
    fun getDrivers(): DriversResponse = DriversProcess().process()


    @Throws(IOException::class, WebserviceException::class)
    fun getDriverDetail(plateNo: String, id: String): DriverDetailResponse = DriverDetailProcess(plateNo, id).process()


    @Throws(IOException::class, WebserviceException::class)
    fun registerDriver(uuid: String, driverId: Int): DriverRegisterResponse = DriverRegisterProcess(uuid, driverId).process()


    @Throws(IOException::class, WebserviceException::class)
    fun driverCode(uuid: String): DriverCodeResponse = DriverCodeProcess(uuid).process()


    @Throws(IOException::class, WebserviceException::class)
    fun login(msisdn: String): LoginResponse = LoginProcess(msisdn).process()

}
