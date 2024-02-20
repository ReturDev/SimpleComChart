package github.returdev.simplecomchart.components.core.graph

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Represents the colors used in the graphic bar chart.
 *
 * @property backgroundColor The background color.
 * @property onBackgroundColor The color when the background is active.
 * @property barColor The color of the bars.
 * @property onBarColor The color of the bars when active.
 * @property selectedBarColor The color of the selected bars.
 * @property onSelectedBarColor The color of the selected bars when active.
 * @property barNameColor The color of the bar names.
 * @property selectedBarNameColor The color of the selected bar names.
 * @property goalIndicatorColor The color of the objective indicator.
 * @property onGoalIndicatorColor The color of the objective text.
 */
@Immutable
class GraphicBarChartColors internal constructor(
    internal val backgroundColor: Color,
    internal val onBackgroundColor: Color,
    internal val goalIndicatorColor: Color,
    internal val onGoalIndicatorColor: Color,
    private val barColor: Color,
    private val onBarColor: Color,
    private val selectedBarColor: Color,
    private val onSelectedBarColor: Color,
    private val barNameColor: Color,
    private val selectedBarNameColor: Color,

    ){

    /**
     * Gets the bar color as a state, depending on whether it's selected or not.
     *
     * @param selected Whether the bar is selected.
     */
    internal fun barColor(selected: Boolean): Color {
        return if (selected) selectedBarColor else barColor
    }

    /**
     * Gets the on-bar color as a state, depending on whether it's selected or not.
     *
     * @param selected Whether the bar is selected.
     */
    internal fun onBarColor(selected: Boolean): Color {
        return if (selected) onSelectedBarColor else onBarColor
    }

    /**
     * Gets the bar name color as a state, depending on whether it's selected or not.
     *
     * @param selected Whether the bar name is selected.
     */
    internal fun barNameColor(selected: Boolean): Color {
        return if (selected) selectedBarNameColor else barNameColor
    }

}