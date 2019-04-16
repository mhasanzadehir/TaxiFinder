package ir.sharif.taxifinder

import android.content.Context
import android.graphics.Typeface
import android.util.DisplayMetrics
import android.widget.TextView

fun TextView.bold(){
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
    return px / (context.getResources().getDisplayMetrics().densityDpi/ DisplayMetrics.DENSITY_DEFAULT)
}

object ScreenValue{
    var width : Int = 0
    var height : Int = 0
}

