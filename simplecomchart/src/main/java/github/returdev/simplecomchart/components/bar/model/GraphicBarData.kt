package github.returdev.simplecomchart.components.bar.model

import androidx.compose.runtime.Immutable


/**
 * Represents the data for a single bar in a graphical display.
 *
 * @property id The unique identifier of the bar.
 * @property barName The name or label associated with the bar.
 * @property barValue The value of the bar.
 * @property barShowValue The formatted value to be displayed on the bar.
 */
@Immutable
data class GraphicBarData(
    val id: Int,
    val barName: String,
    val barValue: Float,
    val barShowValue: String
)
