package ir.sharif.taxifinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import ir.sharif.taxifinder.webservice.WebserviceHelper
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception
import kotlin.concurrent.thread

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        submitButton.bold()
        phoneNumberLabel.bold()

        submitButton.setOnClickListener {
            val phone = phoneEditText.text
            if (phone.startsWith("9") || phone.startsWith("09")) {
                callLoginWebservice(phone.toString())
            } else {
                Toast.makeText(this, "شماره موبایل اشتباه است", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun callLoginWebservice(phone: String) {
        thread(true) {
            try {
                val response = WebserviceHelper.login(phone)
                if (response.code == 200) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    toast(response.message)
                }
            } catch (e: Exception) {
                toastNoNetwork()
            }
        }
    }
}
