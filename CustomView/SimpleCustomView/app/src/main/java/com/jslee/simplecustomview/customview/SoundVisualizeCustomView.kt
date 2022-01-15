package com.jslee.simplecustomview.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.jslee.simplecustomview.R
import kotlin.random.Random

class SoundVisualizeCustomView : View {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?,defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val amplitudePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.getColor(R.color.purple_500)
        strokeWidth = LINE_WIDTH
        strokeCap = Paint.Cap.ROUND
    }

    private var drawingWidth = 0
    private var drawingHeight = 0
    private var drawingAmplitudes: List<Int> = (0..100).map { Random.nextInt(Short.MAX_VALUE.toInt()) }
//    private var drawingAmplitudes: List<Int> = emptyList()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.e("ONMEASURE", "Width : $widthMeasureSpec, Height : $heightMeasureSpec")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.e("ONLAYOUT", "Changed : $changed, Left : $left, Top : $top, Right : $right, Bottom : $bottom")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        drawingWidth = w
        drawingHeight = h

        Log.e("ONSIZECHANGE", "Size Changed with $w & $h")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas ?: return

        val centerY = drawingHeight / 2
        var startingXPosition = drawingWidth.toFloat()

        drawingAmplitudes.forEach { amplitude ->
            val lineLength = amplitude / MAX_AMPLITUDE * drawingHeight * 0.8F

            startingXPosition -= LINE_SPACE

            if (startingXPosition < 0) return@forEach

            canvas.drawLine(
                startingXPosition,
                centerY - lineLength / 2F,
                startingXPosition,
                centerY + lineLength / 2F,
                amplitudePaint
            )
        }

    }

    companion object {
        private const val LINE_WIDTH = 10F
        private const val LINE_SPACE = 15F
        private const val MAX_AMPLITUDE = Short.MAX_VALUE.toFloat()
    }

}