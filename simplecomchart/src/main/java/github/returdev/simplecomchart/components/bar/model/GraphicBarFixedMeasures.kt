package github.returdev.simplecomchart.components.bar.model

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import github.returdev.simplecomchart.components.bar.bubble.model.BubbleFixedMeasures

/**
 * This is an immutable object that contains fixed measures for a graphic bar in a bar chart.
 * It includes the bubble's measures, root width, width, rounded corner shape, text height, minimum bar height greater than 0, and a method to calculate the bar height.
 */
@Immutable
internal object GraphicBarFixedMeasures {

    /**
     * The measures of the bubble.
     * It is an instance of the BubbleFixedMeasures object.
     */
    val bubbleMeasures = BubbleFixedMeasures

    /**
     * The root width of the graphic bar.
     * It is calculated as the total width of the bubble.
     */
    val graphicBarRootWidth = bubbleMeasures.totalBubbleWidth()

    /**
     * The width of the graphic bar.
     * It is calculated as half of the root width of the graphic bar.
     */
    val graphicBarWidth = graphicBarRootWidth / 2

    /**
     * The shape of the graphic bar's corners.
     * It is a RoundedCornerShape object with corner sizes of 5.dp, 5.dp, 2.dp, and 2.dp.
     */
    val graphicBarRoundedCornerShape = RoundedCornerShape(5.dp, 5.dp, 2.dp, 2.dp)

    /**
     * The height of the text in the graphic bar.
     * It is measured in density-independent pixels (dp).
     */
    val graphicTextHeight = 24.dp

    /**
     * The minimum height of the bar when the bar value is greater than 0 and the calculated bar height is 0.dp.
     * It is measured in density-independent pixels (dp).
     */
    private val minimumGreaterThan0BarHeight = 2.dp

    /**
     * This function calculates the height of the bar.
     * It multiplies the content height per unit (in dp) by the bar value (in float).
     * If the calculated bar height is 0.dp and the bar value is greater than 0, it returns the minimum bar height greater than 0.
     * Otherwise, it returns the calculated bar height.
     * @param contentHeightDpPerUnit The content height per unit in density-independent pixels (dp).
     * @param barValue The value of the bar in float.
     * @return The height of the bar in density-independent pixels (dp).
     */
    fun calculateBarHeight(contentHeightDpPerUnit: Dp, barValue: Float): Dp {
        val barHeight = contentHeightDpPerUnit * barValue
        return if (barHeight == 0.dp && barValue > 0) minimumGreaterThan0BarHeight else barHeight
    }

}