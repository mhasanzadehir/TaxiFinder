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
    private val anotherPhoneNumberTextView = itemView.findViewById<TextView>(R.id.anotherPhoneNumber)
    private val profileImage = itemView.findViewById<ImageView>(R.id.profileImage)
    private val carTagImageView = itemView.findViewById<ImageView>(R.id.carTagImageView)
    private val bottomLayout = itemView.findViewById<RelativeLayout>(R.id.bottom_layout)
    private val arrowImage = itemView.findViewById<ImageView>(R.id.arrow)
    private var isExpand = false

    fun bind(driver: Driver) {
        fullNameTextView.text = driver.firstName + " " + driver.lastName
        fullNameTextView.setTypeface(fullNameTextView.typeface, Typeface.BOLD)
        phoneNumberTextView.text = driver.msisdn
        anotherPhoneNumberTextView.text = driver.msisdn
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
        arrowImage.rotation = 0F
    }

    private fun expandView() {
        bottomLayout.visibility = View.VISIBLE
        phoneNumberTextView.visibility = View.GONE
        arrowImage.rotation = 180F
    }
}