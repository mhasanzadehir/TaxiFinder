package ir.sharif.taxifinder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ExpandableListView.OnChildClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create items
        val item1 = AHBottomNavigationItem("QR Scan", R.drawable.qr_code)
        val item2 = AHBottomNavigationItem("List", R.drawable.qr_code)

        // Add items
        bottomNavigation.addItem(item1)
        bottomNavigation.addItem(item2)


        // Set listeners
        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            if (position == 0) {
                val launchIntent = BarcodeReaderActivity.getLaunchIntent(this, true, false)
                startActivityForResult(launchIntent, 100)
            }
            true
        }

        initList()

    }

    private fun initList() {

        val groupData = ArrayList<String>()
        groupData.add("Mobiles")
        groupData.add("Laptops")
        groupData.add("TV")

        val mobileData = ArrayList<String>()
        mobileData.add("Google Pixel")
        mobileData.add("Samsung")
        mobileData.add("OnePlus")
        mobileData.add("LG")
        mobileData.add("Motorola")

        val laptopData = ArrayList<String>()
        laptopData.add("Apple")
        laptopData.add("HP")
        laptopData.add("Dell")
        laptopData.add("Lenovo")
        laptopData.add("Acer")

        val tvData = ArrayList<String>()
        tvData.add("Sony")
        tvData.add("Samsung")
        tvData.add("LG")
        tvData.add("Panasonic")

        val childData = HashMap<String, List<String>>()
        childData[groupData[0]] = mobileData
        childData[groupData[1]] = laptopData
        childData[groupData[2]] = tvData

        // Setting up the Adapter
        val adapter = ExpandableListAdapter(this, groupData, childData)

        listView.setAdapter(adapter)
        // Implementing callback to get notified when a Child item is clicked
        listView.setOnChildClickListener(this)
    }

    override fun onChildClick(
        parent: ExpandableListView?,
        v: View?,
        groupPosition: Int,
        childPosition: Int,
        id: Long
    ): Boolean {
        val childItem = v?.findViewById<TextView>(R.id.tv_child_text)
        val item = childItem?.text.toString()
        Toast.makeText(this, "$item clicked!", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "error in  scanning", Toast.LENGTH_SHORT).show()
            return
        }

        if (requestCode == 100 && data != null) {
            val barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE) as Barcode
            Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show()
        }

    }
}
