package com.shalan.seatmapview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

/**
 * Created by Mohamed Shalan on 2020-02-09.
 */


private const val PADDING = 2f

class SeatView(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0,
    private val defStyleRes: Int = 0,
    override var locationX: Float,
    override var locationY: Float,
    override var seatTextSize: Float = 12f,
    override var seatText: String,
    override var seatColor: Int = Color.BLACK,
    override var seatTextColor: Int = Color.WHITE,
    override var seatShape: Shape = Shape.CIRCLE
) : View(context, attrs, defStyleAttr, defStyleRes), ISeatView {

    constructor(context: Context, attrs: AttributeSet?) : this(
        context = context,
        attrs = attrs,
        locationX = 0f,
        locationY = 0f,
        seatText = ""
    )

    private val viewCoordinatesOnScreen = IntArray(2)

    private val seatTextPaint: Paint

    private val seatTextRect: Rect

    private val seatBackgroundPaint: Paint

    init {

        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SeatView,
            defStyleAttr,
            defStyleRes
        )

        try {

            locationX = typedArray.getDimension(R.styleable.SeatView_seatLocationX, 0f)
            locationY = typedArray.getDimension(R.styleable.SeatView_seatLocationY, 0f)
            seatText = typedArray.getString(R.styleable.SeatView_seatText) ?: ""
            seatTextSize = typedArray.getDimension(R.styleable.SeatView_seatTextSize, 10f)
            seatColor = typedArray.getColor(R.styleable.SeatView_seatColor, Color.BLACK)
            seatTextColor = typedArray.getColor(R.styleable.SeatView_seatTextColor, Color.WHITE)
            seatShape = if (typedArray.getInt(
                    R.styleable.SeatView_seatShape,
                    1
                ) == 1
            ) Shape.CIRCLE else Shape.RECT

        } finally {
            typedArray.recycle()
        }

        seatTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        seatTextPaint.textAlign = Paint.Align.CENTER
        seatTextPaint.color = seatTextColor
        seatTextPaint.textSize = seatTextSize

        seatTextRect = Rect()

        seatBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        seatBackgroundPaint.style = Paint.Style.FILL
        seatBackgroundPaint.color = seatColor


        getLocationOnScreen(viewCoordinatesOnScreen)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        seatTextPaint.getTextBounds(seatText, 0, seatText.length, seatTextRect)
        when (seatShape) {
            Shape.CIRCLE -> canvas.drawCircle(
                (viewCoordinatesOnScreen[0] + width) / 2f,
                (viewCoordinatesOnScreen[1] + height) / 2f,
                width / 2f,
                seatBackgroundPaint
            )
            Shape.RECT -> canvas.drawRect(
                viewCoordinatesOnScreen[0] + PADDING,
                viewCoordinatesOnScreen[1] + PADDING,
                viewCoordinatesOnScreen[0] + width - PADDING,
                viewCoordinatesOnScreen[1] + height - PADDING,
                seatBackgroundPaint
            )
            else -> canvas.drawRect(
                viewCoordinatesOnScreen[0] + PADDING,
                viewCoordinatesOnScreen[1] + PADDING,
                viewCoordinatesOnScreen[0] + width - PADDING,
                viewCoordinatesOnScreen[1] + height - PADDING,
                seatBackgroundPaint
            )
        }

        canvas.drawText(
            seatText,
            width / 2f,
            (height + seatTextRect.height() / 2f) / 2f,
            seatTextPaint
        )
    }
}