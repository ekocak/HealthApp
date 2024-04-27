package com.ekremkocak.healthapp.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random


class SleepCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
    context,
    attrs,
    defStyleAttr
){
    private var max = 0f
    private var min = 300f
    private val radius = 5f // İstediğiniz radius değerini belirtin
    private val list = mutableListOf<Int>()
    private var left = 0f
    private var bot = 0f
    private val mPaint = Paint()

    init {
        //Mock
        for (i in 0 .. 4){

            list.add(10+Random.nextInt(5))
        }
    }
    override fun onDraw(canvas: Canvas) {



        list.forEachIndexed { index, i ->
            mPaint.color = Color.GREEN
            mPaint.alpha = 70
            canvas.drawPath(createBar(index, true),mPaint)
        }

        super.onDraw(canvas)
    }
    private  fun createBar(index : Int,stroke : Boolean): Path{

        val path = Path()
        val radii = floatArrayOf(radius, radius, radius, radius, 0f, 0f, 0f, 0f)
        path.addRoundRect(left, 0f, width.toFloat()/4f-5+left, height.toFloat(), radii, Path.Direction.CW)
        left += width.toFloat()/4f
        return path
    }

    private fun pickColor(): String = when (Random.nextInt(7) + 1) {
        1 -> "#F5CA67"
        2 -> "#0DD3E9"
        3 -> "#DDF17E"
        4 -> "#F11E49"
        5 -> "#2EA154"
        6 -> "#EFE663"
        else -> "#66C46C"
    }
}