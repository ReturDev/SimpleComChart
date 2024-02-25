package github.returdev.simplecomchart.components.goalindicator.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp


/**
 * This is an immutable object that contains fixed measures for a goal indicator in a chart.
 * It includes the goal indicator's size, corner radius, stroke width, and a method to calculate the indicator's Y-axis position.
 */
@Immutable
object GoalIndicatorFixedMeasures {

    /**
     * The size of the goal indicator.
     * It is measured in density-independent pixels (dp) and is represented as a DpSize object.
     */
    val goalIndicatorSize = DpSize(55.dp, 20.dp)

    /**
     * The corner radius of the goal indicator.
     * It is measured in density-independent pixels (dp).
     */
    val goalIndicatorCornerRadius = 5.dp

    /**
     * The stroke width of the goal indicator.
     * It is measured in density-independent pixels (dp).
     */
    val goalIndicatorStrokeWidth = 2.dp

    /**
     * This function calculates the Y-axis position of the goal indicator.
     * It subtracts the product of the content height per unit and the goal value from the total height, and adds the difference between the top content padding and half of the goal indicator's height.
     * @param totalHeight The total height of the chart in density-independent pixels (dp).
     * @param topContentPadding The top content padding of the chart in density-independent pixels (dp).
     * @param contentHeightDpPerUnit The content height per unit in density-independent pixels (dp).
     * @param goalValue The value of the goal in float.
     * @return The Y-axis position of the goal indicator in density-independent pixels (dp).
     */
    fun calculateIndicatorYAxisPosition(
        totalHeight: Dp,
        topContentPadding: Dp,
        contentHeightDpPerUnit: Dp,
        goalValue: Float
    ): Dp {
        return totalHeight - (contentHeightDpPerUnit * goalValue) + (topContentPadding - goalIndicatorSize.height / 2)
    }

}