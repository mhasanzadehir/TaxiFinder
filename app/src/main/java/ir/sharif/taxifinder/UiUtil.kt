package ir.sharif.taxifinder

import android.graphics.Typeface
import android.widget.TextView

fun TextView.bold(){
    typeface = Typeface.create(typeface, Typeface.BOLD)
}