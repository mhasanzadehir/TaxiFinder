package ir.sharif.taxifinder

import android.app.Application
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/iran_sans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}