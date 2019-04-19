package ir.sharif.taxifinder

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import ir.sharif.taxifinder.webservice.WebserviceHelper
import kotlinx.android.synthetic.main.activity_driver_inquiry.*
import java.lang.Exception
import kotlin.concurrent.thread


class DriverInquiryActivity : BaseActivity() {

    var uuid: String? = ""
    var driverCode: String? = ""
    var plate = ""
    var cursor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_inquiry)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.action_bar_blue)
        }
        uuid = intent?.extras?.getString("UUID")
        driverCode = intent?.extras?.getString("DRIVER_CODE")
        initUi()
    }

    private fun initUi() {
        backImageView.setOnClickListener { onBackPressed() }
        footer_bar.setOnClickListener {
            if (cursor == 7) {
                callInquiryWS()
            } else {
                Toast.makeText(this, "شماره پلاک را به صورت کامل وارد کنید", Toast.LENGTH_SHORT).show()
            }
        }

        driverCodeTv.text = FormatHelper.toPersianNumber(driverCode)
        driverCodeLabel.bold()
        driverCodeTv.bold()
        clearFormLabel.bold()

        val adapter = KeyboardAdapter(this, arrayOf(
            R.mipmap.num01,
            R.mipmap.num02,
            R.mipmap.num03,
            R.mipmap.num04,
            R.mipmap.num05,
            R.mipmap.num06,
            R.mipmap.num07,
            R.mipmap.num08,
            R.mipmap.num09
        ), object : KeyListener {
            override fun click(num: Int) {
                if (cursor == 7) return
                plate += num.toString()
                plateView.refresh(plate)
                cursor++
            }
        })

        keyboardLayout.layoutManager = GridLayoutManager(this, 3)

        keyboardLayout.adapter = adapter

        clearLayout.setOnClickListener {
            plateView.refresh("")
            cursor = 0
            plate = ""
        }
    }


    private fun callInquiryWS() {
        thread(true) {
            try {
                val response = WebserviceHelper.getDriverDetail(normalizePlateToWS(plate), uuid!!)
                if (response.code == 200) {
                    DataRepo.driverDetail = response.driverDetail
                    val intent = Intent(this, DriverDetailActivity::class.java)
                    intent.putExtra("PLATE", plate)
                    startActivity(intent)
                } else {
                    toast(response.message)
                }
            }catch (e: Exception){
                toastNoNetwork()
            }
        }
    }

}
