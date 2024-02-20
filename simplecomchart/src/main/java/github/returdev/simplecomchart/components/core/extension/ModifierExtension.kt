package github.returdev.simplecomchart.components.core.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

/**
 * Adds a clickable interaction to the modifier without ripple effect.
 *
 * @param onClick The callback to be invoked when the composable is clicked.
 * @return The modified modifier with the clickable interaction without ripple effect.
 */
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = this.composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}