package ir.sharif.taxifinder

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import ir.sharif.taxifinder.webservice.WebserviceHelper
import kotlinx.android.synthetic.main.activity_driver_inquiry.*
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
            callInquiryWS()
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
                addCarTag(num.toString(), cursor++, carTagImageView, rootLayout)
            }
        })

        keyboardLayout.layoutManager = GridLayoutManager(this, 3)

        keyboardLayout.adapter = adapter

        clearLayout.setOnClickListener {
            rootLayout.removeAllViews()
            cursor = 0
            plate = ""
        }
    }

    fun normalizePlate(plate: String):String{
        var result = ""
        result+=plate[5]
        result+=plate[6]
        result+=plate[2]
        result+=plate[3]
        result+=plate[4]
        result+="04"
        result+=plate[0]
        result+=plate[1]
        return result
    }
    private fun callInquiryWS() {
        thread(true) {
            val response = WebserviceHelper.getDriverDetail(normalizePlate(plate), uuid!!)
            if (response.code == 200) {
                DataRepo.driverDetail = response.driverDetail
                val intent = Intent(this, DriverDetailActivity::class.java)
                intent.putExtra("PLATE", plate)
                startActivity(intent)
            } else{
                runOnUiThread {
                    Toast.makeText(this, response.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    private val positionsX = arrayOf(48, 73, 166, 196, 231, 272, 305)


    private fun addCarTag(char: String, position: Int, imageView: ImageView, layout: FrameLayout) {
        plate += char
        addDigitToCarTag(getSource(char[0]), positionsX[position], layout, imageView)
    }

    private fun getSource(char: Char): Int {
        return when (char) {
            '1' -> R.mipmap.num01
            '2' -> R.mipmap.num02
            '3' -> R.mipmap.num03
            '4' -> R.mipmap.num04
            '5' -> R.mipmap.num05
            '6' -> R.mipmap.num06
            '7' -> R.mipmap.num07
            '8' -> R.mipmap.num08
            '9' -> R.mipmap.num09
            else -> R.mipmap.num09
        }
    }


    private fun addDigitToCarTag(
        source: kotlin.Int,
        positionX: kotlin.Int,
        rootLayout: FrameLayout,
        imageView: ImageView
    ) {
        val num = ImageView(this@DriverInquiryActivity)
        num.setImageResource(source)
        num.tag = "num"
        val width = imageView.measuredWidth
        val height = imageView.measuredHeight
        val ratio = width.toFloat() / 341.toFloat()
        val w = 20 * ratio
        val h = 30 * ratio
        val array = IntArray(2)
        imageView.getLocationInWindow(array)
        val params = FrameLayout.LayoutParams(w.toInt(), h.toInt())
        params.leftMargin = (array[0] + positionX * ratio).toInt()
        params.topMargin = (array[1] - getStatusBarHeight() + 54 * ratio - h).toInt()
        rootLayout.addView(num, params)
    }


}
