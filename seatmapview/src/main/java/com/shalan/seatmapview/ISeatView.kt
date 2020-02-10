package com.shalan.seatmapview

import androidx.annotation.ColorInt

/**
 * Created by Mohamed Shalan on 2020-02-09.
 */
interface ISeatView {

    var locationX: Float

    var locationY: Float

    var seatTextSize: Float

    var seatText: String

    @get:ColorInt
    var seatColor: Int

    @get:ColorInt
    var seatTextColor: Int

    var seatShape: Shape

}