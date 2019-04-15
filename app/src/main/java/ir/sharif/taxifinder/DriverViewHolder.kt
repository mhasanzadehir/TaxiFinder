package ir.sharif.taxifinder

import android.content.Intent
import android.graphics.Typeface
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.sharif.taxifinder.webservice.webservices.drivers.Driver

class DriverViewHolder(itemView: View, val rootLayout: RelativeLayout) : RecyclerView.ViewHolder(itemView) {
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
        addCarTag("1234567", carTagImageView, rootLayout)
        Glide.with(itemView.context).load(driver.imageUrl).into(profileImage)
        profileImage.setOnClickListener {
            ContextCompat.startActivity(itemView.context, Intent(itemView.context, DriverDetailActivity::class.java), null)
        }
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

    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = itemView.context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = itemView.context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    private fun addCarTag(number: String, imageView: ImageView, layout: RelativeLayout) {
        val positionsX = arrayOf(48,73, 166, 196, 231, 272, 305)
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
                val num = ImageView(itemView.context)
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

}