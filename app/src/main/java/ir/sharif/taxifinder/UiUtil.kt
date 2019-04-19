package ir.sharif.taxifinder

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.util.DisplayMetrics
import android.widget.TextView
import android.widget.Toast

fun TextView.bold() {
    typeface = Typeface.create(typeface, Typeface.BOLD)
}


/**
 * This method converts dp unit to equivalent pixels, depending on device density.
 *
 * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
 * @param context Context to get resources and device specific display metrics
 * @return A float value to represent px equivalent to dp depending on device density
 */
fun convertDpToPixel(dp: Float, context: Context): Float {
    return dp * (context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

/**
 * This method converts device specific pixels to density independent pixels.
 *
 * @param px A value in px (pixels) unit. Which we need to convert into db
 * @param context Context to get resources and device specific display metrics
 * @return A float value to represent dp equivalent to px value
 */
fun convertPixelsToDp(px: Float, context: Context): Float {
    return px / (context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

object ScreenValue {
    var width: Int = 0
    var height: Int = 0
}

fun Activity.toastNoNetwork() {
    runOnUiThread {
        Toast.makeText(this, "اتصال به شبکه خود را بررسی کنید و مجددا تلاش کنید", Toast.LENGTH_SHORT).show()
    }
}

fun Activity.toast(message: String) {
    runOnUiThread {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun normalizePlateToWS(plate: String): String {
    var result = ""
    result += plate[5]
    result += plate[6]
    result += plate[2]
    result += plate[3]
    result += plate[4]
    result += "04"
    result += plate[0]
    result += plate[1]
    return result
}

fun normalizePlate(plate: String): String {
    var result = ""
    result += plate[7]
    result += plate[8]
    result += plate[2]
    result += plate[3]
    result += plate[4]
    result += plate[0]
    result += plate[1]
    return result

}

