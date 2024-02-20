package github.returdev.simplecomchart.components.bar.bubble.model

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

/**
 * This is an immutable object that contains fixed measures for a bubble in a bar chart.
 * It includes the bubble's bottom padding, size, rounded corner shape, tail size, and methods to calculate the total height and width.
 */
@Immutable
internal object BubbleFixedMeasures {

    /**
     * The padding at the bottom of the bubble.
     * It is measured in density-independent pixels (dp).
     */
    val bubbleBottomPadding: Dp = 4.dp

    /**
     * The size of the bubble.
     * It is measured in density-independent pixels (dp) and is represented as a DpSize object.
     */
    val bubbleSize = DpSize(40.dp, 20.dp)

    /**
     * The shape of the bubble's corners.
     * It is a RoundedCornerShape object with a corner size of 5.dp.
     */
    val bubbleRoundedCornerShape = RoundedCornerShape(5.dp)

    /**
     * The size of the bubble's tail.
     * It is measured in density-independent pixels (dp) and is represented as a DpSize object.
     */
    val bubbleTailSize = DpSize(10.dp, 5.dp)

    /**
     * This function calculates the total height of the bubble.
     * It adds the height of the bubble, the height of the bubble's tail, and the bubble's bottom padding.
     * @return The total height of the bubble in density-independent pixels (dp).
     */
    fun totalBubbleHeight() : Dp = bubbleSize.height + bubbleTailSize.height + bubbleBottomPadding

    /**
     * This function calculates the total width of the bubble.
     * It returns the width of the bubble.
     * @return The total width of the bubble in density-independent pixels (dp).
     */
    fun totalBubbleWidth() : Dp = bubbleSize.width
}