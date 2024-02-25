package github.returdev.simplecomchart.components.barchart.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import github.returdev.simplecomchart.components.bar.model.GraphicBarFixedMeasures
import github.returdev.simplecomchart.components.goalindicator.model.GoalIndicatorFixedMeasures

/**
 * An immutable object that holds fixed measures for a bar chart.
 *
 * @property graphicBarMeasures Measures related to the graphic bars in the chart.
 * @property goalIndicatorMeasures Measures related to the goal indicator used in the chart.
 * @property backgroundCornerSize The corner size for the background of the chart.
 * @property topContentPadding Padding at the top of the content area.
 * @property bottomContentPadding Padding at the bottom of the content area.
 * @property yAxisLinesStartPadding Padding for the start of Y-axis lines.
 * @property startYAxisSpace Start Y-axis space for the chart.
 *
 */
@Immutable
internal object BarChartFixedMeasures {

    /** Measures related to the graphic bars in the chart. */
    val graphicBarMeasures = GraphicBarFixedMeasures

    /** Measures related to the goal indicator used in the chart. */
    val goalIndicatorMeasures = GoalIndicatorFixedMeasures

    /** The corner size for the background of the chart. */
    val backgroundCornerSize = 8.dp

    /** Padding at the top of the content area. */
    val topContentPadding = graphicBarMeasures.bubbleMeasures.totalBubbleHeight()

    /** Padding at the bottom of the content area. */
    val bottomContentPadding = graphicBarMeasures.graphicTextHeight

    /** Padding for the start of Y-axis lines. */
    val yAxisLinesStartPadding = 4.dp

    /** Start Y-axis space for the chart. */
    val startYAxisSpace = goalIndicatorMeasures.goalIndicatorSize.width

    /**
     * Calculates the padding needed at the top of the content area to center the content vertically.
     *
     * @param contentDrawsHeight The height of the content to be drawn.
     * @return The calculated padding.
     */
    fun calculateTopContentDrawsPadding(
        contentDrawsHeight: Dp
    ) = topContentPadding - contentDrawsHeight / 2

    /**
     * Calculates the total content height of the chart.
     *
     * @param graphicBarChartHeight The height of the graphic bar chart.
     * @return The total content height.
     */
    fun calculateTotalContentHeight(
        graphicBarChartHeight: Dp
    ) = graphicBarChartHeight - (topContentPadding + bottomContentPadding)


}