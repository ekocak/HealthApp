package com.ekremkocak.healthapp.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.view.View

class CustomProgress(context: Context) : View(context){
    private val radius = 20.0f // İstediğiniz radius değerini belirtin
    override fun onDraw(c: Canvas) {
        val path = Path()
        val radii = floatArrayOf(radius, radius, radius, radius, 0.0f, 0.0f, 0.0f, 0.0f)
        path.addRoundRect(0f, 0f, width.toFloat(), height.toFloat(), radii, Path.Direction.CW)
        c.clipPath(path)
        super.onDraw(c)
    }
}