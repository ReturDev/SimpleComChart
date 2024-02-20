package github.returdev.simplecomchart.components.unit

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

/**
 * Composable function to display unit text in a graphic chart.
 *
 * @param modifier The modifier to be applied to the unit text.
 * @param unit The unit text to be displayed.
 * @param textColor The color of the unit text.
 * @param textStyle The style of the unit text.
 */
@Composable
internal fun BarChartUnitText(
    modifier: Modifier = Modifier,
    unit: String,
    textColor: Color,
    textStyle : TextStyle
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = unit,
            color = textColor,
            style = textStyle
        )
    }
}