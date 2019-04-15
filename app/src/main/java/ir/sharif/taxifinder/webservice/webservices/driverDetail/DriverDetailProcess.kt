package ir.sharif.taxifinder.webservice.webservices.driverDetail

import java.io.IOException

import ir.sharif.taxifinder.webservice.base.BaseProcess
import ir.sharif.taxifinder.webservice.base.MyRetrofit
import ir.sharif.taxifinder.webservice.base.WebserviceException

class DriverDetailProcess(plateNo: String, id: String) : BaseProcess() {
    private val request: DriverDetailRequest = DriverDetailRequest(plateNo, id)

    @Throws(IOException::class, WebserviceException::class)
    override fun process() = send(MyRetrofit.webserviceUrls.driverDetail(request))
}
