package ir.sharif.taxifinder

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

private const val STICKY_WIDTH_UNDEFINED = -1
private const val BREAK_HEIGHT = 1950
private const val ARBITRARY_WIDTH_LESSER = 341
private const val ARBITRARY_WIDTH_GREATER = 682
private const val mStickyWidth = STICKY_WIDTH_UNDEFINED
private val positionsX = arrayOf(48, 73, 166, 196, 231, 272, 305)

class PlateView : View {
    lateinit var num1: Bitmap
    lateinit var num2: Bitmap
    lateinit var num3: Bitmap
    lateinit var num4: Bitmap
    lateinit var num5: Bitmap
    lateinit var num6: Bitmap
    lateinit var num7: Bitmap
    lateinit var num8: Bitmap
    lateinit var num9: Bitmap
    var number: String = ""

    constructor(context: Context?) : super(context){init()}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){ init() }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){init()}


    private fun init(){
        num1 = BitmapFactory.decodeResource(context.resources, R.mipmap.num01)
        num2 = BitmapFactory.decodeResource(context.resources, R.mipmap.num02)
        num3 = BitmapFactory.decodeResource(context.resources, R.mipmap.num03)
        num4 = BitmapFactory.decodeResource(context.resources, R.mipmap.num04)
        num5 = BitmapFactory.decodeResource(context.resources, R.mipmap.num05)
        num6 = BitmapFactory.decodeResource(context.resources, R.mipmap.num06)
        num7 = BitmapFactory.decodeResource(context.resources, R.mipmap.num07)
        num8 = BitmapFactory.decodeResource(context.resources, R.mipmap.num08)
        num9 = BitmapFactory.decodeResource(context.resources, R.mipmap.num09)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val desiredHeight = 10000 // some value that is too high for the screen
        val desiredWidth: Int

        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

        val width: Int
        val height: Int

        // Height
        height = when (heightMode) {
            View.MeasureSpec.EXACTLY -> heightSize
            View.MeasureSpec.AT_MOST -> Math.min(desiredHeight, heightSize)
            View.MeasureSpec.UNSPECIFIED -> desiredHeight
            else -> desiredHeight
        }

        // Width
        desiredWidth = when {
            mStickyWidth != STICKY_WIDTH_UNDEFINED -> // This is the second time through layout and we are trying renogitiate a greater
                // width (mStickyWidth) without breaking the contract with the View.
                mStickyWidth
            height > BREAK_HEIGHT -> // a number between onMeasure's two final height requirements
                ARBITRARY_WIDTH_LESSER // arbitrary number
            else -> ARBITRARY_WIDTH_GREATER // arbitrary number
        }

        width = when (widthMode) {
            View.MeasureSpec.EXACTLY -> widthSize
            View.MeasureSpec.AT_MOST -> Math.min(desiredWidth, widthSize)
            View.MeasureSpec.UNSPECIFIED-> desiredWidth
            else -> desiredWidth
        }

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (number.isEmpty()) return
        var i = 0
        for (char in number) {
            val ratio = width.toFloat() / 341.toFloat()
            val w = 20 * ratio
            val h = 30 * ratio
            val startX = (positionsX[i] * ratio).toInt()
            val startY = (54 * ratio).toInt()
            val rect = Rect(startX, startY - h.toInt(), startX + w.toInt(), startY)
            canvas?.drawBitmap(getBitmap(char), null,  rect, null)
            i++
        }
    }


    private fun getBitmap(char: Char): Bitmap {
        return when (char) {
            '1' -> num1
            '2' -> num2
            '3' -> num3
            '4' -> num4
            '5' -> num5
            '6' -> num6
            '7' -> num7
            '8' -> num8
            '9' -> num9
            else -> num9
        }
    }

    fun refresh(number: String){
        this.number = number
        invalidate()
    }


}