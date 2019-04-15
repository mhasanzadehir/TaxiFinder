package ir.sharif.taxifinder.webservice.webservices.driverRegister

import java.io.IOException

import ir.sharif.taxifinder.webservice.base.BaseProcess
import ir.sharif.taxifinder.webservice.base.MyRetrofit
import ir.sharif.taxifinder.webservice.base.WebserviceException
import ir.sharif.taxifinder.webservice.webservices.driverRegister.DriverRegisterRequest

class DriverRegisterProcess(uuid: Int, driverId: Int) : BaseProcess() {
    private val request: DriverRegisterRequest = DriverRegisterRequest(uuid, driverId)

    @Throws(IOException::class, WebserviceException::class)
    override fun process() = send(MyRetrofit.webserviceUrls.driverRegister(request))
}
