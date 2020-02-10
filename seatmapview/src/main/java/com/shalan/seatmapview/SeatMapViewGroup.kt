package com.shalan.seatmapview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children

/**
 * Created by Mohamed Shalan on 2020-02-10.
 */
class SeatMapViewGroup : ViewGroup {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (child in children) {
            if (child is SeatView) {
                val layoutParams = child.layoutParams
                child.layout(
                    child.locationX.toInt(),
                    child.locationY.toInt(),
                    (child.locationX + layoutParams.width).toInt(),
                    (child.locationY + layoutParams.height).toInt()
                )
            } else {
                throw IllegalArgumentException(
                    "${SeatMapViewGroup::class.java.simpleName} only " +
                            "accept ${SeatView::class.java.simpleName} as its child"
                )
            }
        }
    }
}