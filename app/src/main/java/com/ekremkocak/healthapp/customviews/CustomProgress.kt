package com.ekremkocak.healthapp.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random


class CustomProgress @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
    context,
    attrs,
    defStyleAttr
){
    private val radius = 20f // İstediğiniz radius değerini belirtin
    private val list = mutableListOf<Int>(1,2,3,1,2,3)
    private var left = 0f
    private val mPaint = Paint()
    override fun onDraw(canvas: Canvas) {


        list.forEachIndexed { index, i ->

            mPaint.color = Color.parseColor(pickColor())
            canvas.drawPath(createBar(index),mPaint)

        }

        super.onDraw(canvas)
    }
    private  fun createBar(index : Int): Path{
        var total = 0f
        list.forEach { total += it }
        val firstRadius = if (index == 0) radius else 0f
        val lastRadius = if (index == list.size-1) radius else 0f
        val path = Path()
        val radii = floatArrayOf(firstRadius, firstRadius, lastRadius, lastRadius, lastRadius, lastRadius, firstRadius, firstRadius)
        path.addRoundRect(left, 0f, width.toFloat()*(list[index]/total)+left, height.toFloat(), radii, Path.Direction.CW)
        left += width.toFloat()*(list[index]/total)
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