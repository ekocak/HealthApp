package com.ekremkocak.healthapp.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.ekremkocak.healthapp.data.model.Bpm
import kotlin.random.Random


class BpmCustomView @JvmOverloads constructor(
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
    private val radius = 20f // İstediğiniz radius değerini belirtin
    private val list = mutableListOf<Bpm>()
    private var left = 0f
    private var bot = 0f
    private val mPaint = Paint()

    init {
        //Mock
        for (i in 0 .. 24){
            val offset = Random.nextInt(15)
            if(offset+50 < min )min = offset+50f
            val value = Random.nextInt(15)
            if(50+offset+value > max )max = 50f+offset+value
            list.add(Bpm(50+offset,50+offset+value))
        }
    }
    override fun onDraw(canvas: Canvas) {


        list.forEachIndexed { index, i ->

            mPaint.color = Color.parseColor(pickColor())
            canvas.drawPath(createBar(index),mPaint)
            
        }

        super.onDraw(canvas)
    }
    private  fun createBar(index : Int): Path{

        val start = height.toFloat()*(list[index].start/min-1)
        val end = height.toFloat()-(1-list[index].end/max)*height.toFloat()
        val path = Path()
        val radii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
        path.addRoundRect(left, start, width.toFloat()/24f+left-3, end, radii, Path.Direction.CW)
        println("start : "+start+" - "+height)
        left += width.toFloat()/24f
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