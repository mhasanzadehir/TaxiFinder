package ir.sharif.taxifinder

import android.content.Intent
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
        DataRepo.driverDetail ?: return
        driverDetail = DataRepo.driverDetail!!
        initUi()
        val plate = intent?.extras?.getString("PLATE")
        plate ?: return
        plateView.refresh(plate)
    }

    private fun initUi() {
        backImageView.setOnClickListener { onBackPressed() }
        footer_bar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        Glide.with(this).load(driverDetail.imageUrl).into(profileImage)
        fullName.text = driverDetail.firstName + " " + driverDetail.lastName
        brandTextView.text = driverDetail.car.brand
        modelTextView.text = driverDetail.car.model
        yearTextView.text = FormatHelper.toPersianNumber(driverDetail.car.createdYear)
        usageTextView.text = driverDetail.car.usage
        companyTextView.text = driverDetail.car.company
        colorTextView.text = driverDetail.car.color
        phoneNumberTextView.text = FormatHelper.toPersianNumber(driverDetail.msisdn)

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
