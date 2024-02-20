package github.returdev.simplecomchart.core.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

/**
 * Converts pixels to density-independent pixels (dp).
 *
 * @return The value in density-independent pixels (dp).
 */
@Composable
fun Int.pixelsToDp(): Dp {
    return with(LocalDensity.current) {
        this@pixelsToDp.toDp()
    }
}