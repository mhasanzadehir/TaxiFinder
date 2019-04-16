package ir.sharif.taxifinder

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import ir.sharif.taxifinder.webservice.WebserviceHelper
import ir.sharif.taxifinder.webservice.webservices.drivers.Driver
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : BaseActivity() {

    var drivers : List<Driver> = arrayListOf(Driver("https://www.highwaytohimalayas.com//user_upload/images/5899ffcf6e09a897008b5c04-750-750.jpg",
        "مهدی", "حسن زاده", "12345", "سمند", "۴۳ت۷۵۶"),
        Driver("https://www.highwaytohimalayas.com//user_upload/images/5899ffcf6e09a897008b5c04-750-750.jpg",
            "مهدی", "حسن زاده", "12345", "سمند", "۴۳ت۷۵۶"),
        Driver("https://www.highwaytohimalayas.com//user_upload/images/5899ffcf6e09a897008b5c04-750-750.jpg",
            "مهدی", "حسن زاده", "12345", "سمند", "۴۳ت۷۵۶"),
        Driver("https://www.highwaytohimalayas.com//user_upload/images/5899ffcf6e09a897008b5c04-750-750.jpg",
            "مهدی", "حسن زاده", "12345", "سمند", "۴۳ت۷۵۶"),
        Driver("https://www.highwaytohimalayas.com//user_upload/images/5899ffcf6e09a897008b5c04-750-750.jpg",
            "مهدی", "حسن زاده", "12345", "سمند", "۴۳ت۷۵۶"),
        Driver("https://www.highwaytohimalayas.com//user_upload/images/5899ffcf6e09a897008b5c04-750-750.jpg",
            "مهدی", "حسن زاده", "12345", "سمند", "۴۳ت۷۵۶")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create items
        val item1 = AHBottomNavigationItem(R.string.scan_barcode, R.drawable.qrcode, R.color.white)
        val item2 = AHBottomNavigationItem(R.string.list, R.drawable.list, R.color.white)

        // Add items
        bottomNavigation.addItem(item1)
        bottomNavigation.addItem(item2)

        bottomNavigation.defaultBackgroundColor = Color.parseColor("#2452C1")
        // Change colors
        bottomNavigation.accentColor = Color.WHITE
        bottomNavigation.inactiveColor = Color.WHITE

        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW

        bottomNavigation.setTitleTextSize(26F, 26F)

        // Set listeners
        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            if (position == 0) {
                val launchIntent = BarcodeReaderActivity.getLaunchIntent(this, true, false)
                startActivityForResult(launchIntent, 100)
            } else {
                callWebservice()
            }
            true
        }

        initList()
//        callWebservice()

    }

    private fun callWebservice() {
        thread(true) {
            val response = WebserviceHelper.getDrivers()
            if(response.code == 200){
                drivers = response.driver
                listView.adapter?.notifyDataSetChanged()
            } else{
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun initList() {

        val adapter = DriverAdapter(this, drivers)

        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "error in  scanning", Toast.LENGTH_SHORT).show()
            return
        }

        if (requestCode == 100 && data != null) {
            val barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE) as Barcode
            callInquiryWebservice(barcode.rawValue)
        }

    }

    private fun callInquiryWebservice(uuid: String) {
        thread(true) {
            val response = WebserviceHelper.driverCode(uuid)
            val intent = Intent(this, DriverInquiryActivity::class.java)
            intent.putExtra("UUID", uuid)
            intent.putExtra("DRIVER_CODE", response.driverCode)
            startActivity(intent)
        }
    }
}
