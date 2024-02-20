package github.returdev.simplecomchart.core.model.graph

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Default values for a graphic bar chart.
 */
object BarChartDefaults {

    /**
     * Creates a [BarChartColors] object with the specified color values.
     *
     * @param backgroundColor The background color of the chart. Defaults to [Color.Transparent].
     * @param onBackgroundColor The color for elements on the background. Defaults to the current local content color.
     * @param barColor The color of the bars in the chart.
     * @param onBarColor The color of elements on the bars in the chart.
     * @param selectedBarColor The color of selected bars in the chart.
     * @param onSelectedBarColor The color of elements on selected bars in the chart.
     * @param barNameColor The color of the names associated with the bars in the chart.
     * @param selectedBarNameColor The color of the names associated with selected bars in the chart.
     * @param goalIndicatorColor The color of the goal indicator in the chart.
     * @param onGoalIndicatorColor The color of elements on the goal indicator in the chart.
     * @return [BarChartColors] object with the specified color values.
     */
    @Composable
    fun graphicChartColors(
        backgroundColor: Color = Color.Transparent,
        onBackgroundColor: Color = LocalContentColor.current,
        barColor: Color,
        onBarColor: Color,
        selectedBarColor: Color,
        onSelectedBarColor: Color,
        barNameColor: Color,
        selectedBarNameColor: Color,
        goalIndicatorColor: Color,
        onGoalIndicatorColor: Color
    ): BarChartColors = BarChartColors(
        backgroundColor = backgroundColor,
        onBackgroundColor = onBackgroundColor,
        goalIndicatorColor = goalIndicatorColor,
        onGoalIndicatorColor = onGoalIndicatorColor,
        barColor = barColor,
        onBarColor = onBarColor,
        selectedBarColor = selectedBarColor,
        onSelectedBarColor = onSelectedBarColor,
        barNameColor = barNameColor,
        selectedBarNameColor = selectedBarNameColor
    )

    /**
     * Creates default graphic chart text styles.
     *
     * @param barChartBubbleStyle The style for the bar chart bubble.
     * @param barChartNameStyle The style for the bar chart name.
     * @param objectiveIndicatorStyle The style for the objective indicator.
     * @param graphicValueIndicatorStyle The style for the graphic value indicator.
     *
     * @return The [BarChartTextStyles] object with the default text styles.
     */
    @Composable
    fun graphicBarChartTextStyles(
        barChartBubbleStyle : TextStyle = LocalTextStyle.current.copy(fontSize = 10.sp),
        barChartNameStyle : TextStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
        graphicUnitStyle : TextStyle = LocalTextStyle.current.copy(fontSize = 11.sp, fontWeight = FontWeight.SemiBold),
        objectiveIndicatorStyle : TextStyle = LocalTextStyle.current.copy(fontSize = 11.sp),
        graphicValueIndicatorStyle : TextStyle = LocalTextStyle.current.copy(fontSize = 12.sp, fontWeight = FontWeight.Bold),
    ) = BarChartTextStyles(
        barChartBubbleStyle,
        barChartNameStyle,
        graphicUnitStyle,
        objectiveIndicatorStyle,
        graphicValueIndicatorStyle
    )

}
