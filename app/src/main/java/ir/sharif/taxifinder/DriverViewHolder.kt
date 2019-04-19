package ir.sharif.taxifinder

import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.sharif.taxifinder.webservice.webservices.drivers.Driver

class DriverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val fullNameTextView = itemView.findViewById<TextView>(R.id.fullName)
    private val phoneNumberTextView = itemView.findViewById<TextView>(R.id.phoneNumber)
    private val brandTextView = itemView.findViewById<TextView>(R.id.brandName)
    private val anotherPhoneNumberTextView = itemView.findViewById<TextView>(R.id.anotherPhoneNumber)
    private val profileImage = itemView.findViewById<ImageView>(R.id.profileImage)
    private val plateView = itemView.findViewById<PlateView>(R.id.plateView)
    private val bottomLayout = itemView.findViewById<RelativeLayout>(R.id.bottom_layout)
    private val arrowImage = itemView.findViewById<ImageView>(R.id.arrow)
    private var isExpand = false

    fun bind(driver: Driver) {
        plateView.refresh(normalizePlate(driver.plate))
        fullNameTextView.text = driver.firstName + " " + driver.lastName
        fullNameTextView.setTypeface(fullNameTextView.typeface, Typeface.BOLD)
        phoneNumberTextView.text = FormatHelper.toPersianNumber(driver.msisdn)
        phoneNumberTextView.bold()
        brandTextView.text = driver.carBrand
        brandTextView.bold()

        anotherPhoneNumberTextView.text = FormatHelper.toPersianNumber(driver.msisdn)
        phoneNumberTextView.setTypeface(phoneNumberTextView.typeface, Typeface.BOLD)
        anotherPhoneNumberTextView.setTypeface(anotherPhoneNumberTextView.typeface, Typeface.BOLD)
        Glide.with(itemView.context).load(driver.imageUrl).into(profileImage)
        itemView.findViewById<CardView>(R.id.card_view).setOnClickListener {
            isExpand = if (!isExpand) {
                expandView()
                true
            } else {
                collapseView()
                false
            }
        }

    }

    private fun collapseView() {
        bottomLayout.visibility = View.GONE
        phoneNumberTextView.visibility = View.VISIBLE
        brandTextView.visibility = View.GONE
        arrowImage.rotation = 0F
    }

    private fun expandView() {
        bottomLayout.visibility = View.VISIBLE
        phoneNumberTextView.visibility = View.GONE
        brandTextView.visibility = View.VISIBLE
        arrowImage.rotation = 180F
    }

}