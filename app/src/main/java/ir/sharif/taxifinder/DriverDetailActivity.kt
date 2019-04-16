package ir.sharif.taxifinder

import android.os.Build
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import ir.sharif.taxifinder.webservice.webservices.driverDetail.Car
import ir.sharif.taxifinder.webservice.webservices.driverDetail.DriverDetail
import kotlinx.android.synthetic.main.activity_driver_detail.*

class DriverDetailActivity : BaseActivity() {


    lateinit var driverDetail: DriverDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_detail)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.action_bar_blue)
        }
        driverDetail = intent?.extras?.getSerializable("DRIVER_DETAIL") as DriverDetail
        initUi()
        addCarTag("9898988", carTagImageView, rootLayout)
    }

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    private fun addCarTag(number: String, imageView: ImageView, layout: RelativeLayout) {
        val positionsX = arrayOf(48, 73, 166, 196, 231, 272, 305)
        imageView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                imageView.viewTreeObserver.removeOnPreDrawListener(this)

                for (i in 0 until number.length) {
                    val char = number[i]
                    addDigitToCarTag(getSource(char), positionsX[i], layout)
                }
                return true
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
                rootLayout: android.widget.RelativeLayout
            ) {
                val num = ImageView(this@DriverDetailActivity)
                num.setImageResource(source)
                val width = imageView.measuredWidth
                val height = imageView.measuredHeight
                val ratio = width.toFloat() / 341.toFloat()
                val w = 20 * ratio
                val h = 30 * ratio
                val array = IntArray(2)
                imageView.getLocationInWindow(array)
                val params = RelativeLayout.LayoutParams(w.toInt(), h.toInt())
                params.leftMargin = (array[0] + positionX * ratio).toInt()
                params.topMargin = (array[1] - getStatusBarHeight() + 54 * ratio - h).toInt()
                rootLayout.addView(num, params)
            }

        })

    }

    private fun initUi() {
        backImageView.setOnClickListener { onBackPressed() }
        footer_bar.setOnClickListener { onBackPressed() }
        Glide.with(this).load(driverDetail.imageUrl).into(profileImage)
        fullName.text = driverDetail.firstName + " " + driverDetail.lastName
        brandTextView.text = driverDetail.car.brand
        modelTextView.text = driverDetail.car.model
        yearTextView.text = driverDetail.car.createdYear
        usageTextView.text = driverDetail.car.usage
        companyTextView.text = driverDetail.car.company
        colorTextView.text = driverDetail.car.color
        phoneNumberTextView.text = driverDetail.msisdn

        phoneNumberTextView.bold()
        brandTextView.bold()
        brandLabel.bold()
        modelTextView.bold()
        modelLabel.bold()
        yearTextView.bold()
        yearLabel.bold()
        usageTextView.bold()
        usageLabel.bold()
        companyTextView.bold()
        companyLabel.bold()
        colorTextView.bold()
        colorLabel.bold()
    }
}
