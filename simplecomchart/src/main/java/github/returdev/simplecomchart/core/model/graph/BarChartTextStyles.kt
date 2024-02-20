package github.returdev.simplecomchart.core.model.graph

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle


/**
 * Immutable data class representing text styles used in a graphic bar chart.
 *
 * @property barChartBubbleStyle The text style for the bubble in the bar chart.
 * @property barChartNameStyle The text style for the names of bars in the bar chart.
 * @property graphicUnitStyle The text style for the unit displayed in the bar chart.
 * @property goalIndicatorStyle The text style for the goal indicator in the bar chart.
 * @property graphicValueIndicatorStyle The text style for the value indicator in the bar chart.
 */
@Immutable
data class BarChartTextStyles internal constructor(
    internal val barChartBubbleStyle : TextStyle,
    internal val barChartNameStyle : TextStyle,
    internal val graphicUnitStyle : TextStyle,
    internal val goalIndicatorStyle : TextStyle,
    internal val graphicValueIndicatorStyle : TextStyle
)