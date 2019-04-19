package ir.sharif.taxifinder.webservice.webservices.login

import java.io.IOException

import ir.sharif.taxifinder.webservice.base.BaseProcess
import ir.sharif.taxifinder.webservice.base.MyRetrofit
import ir.sharif.taxifinder.webservice.base.WebserviceException
import ir.sharif.taxifinder.webservice.webservices.driverRegister.DriverRegisterRequest

class LoginProcess(val mssidn: String) : BaseProcess() {

    @Throws(IOException::class, WebserviceException::class)
    override fun process() = send(MyRetrofit.webserviceUrls.login(mssidn))
}
