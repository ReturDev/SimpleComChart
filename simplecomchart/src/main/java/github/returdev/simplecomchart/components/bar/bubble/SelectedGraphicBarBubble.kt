package github.returdev.simplecomchart.components.bar.bubble

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.DpSize


/**
 * Composable function representing the tail part of the selected graphic bar bubble.
 *
 * @param bubbleTailSize The size of the bubble tail.
 * @param bubbleColor The color of the bubble tail.
 */
@Composable
private fun BubbleTail(
    bubbleTailSize : DpSize,
    bubbleColor : Color
) {

    Canvas(
        modifier = Modifier.size( bubbleTailSize )
    ){
        drawPath(
            path = Path().apply {
                moveTo(x = 0f, y = 0f)
                lineTo(
                    x = size.width / 2,
                    y = size.height
                )
                lineTo(
                    x = size.width,
                    y = 0f
                )
                close()
            },
            color = bubbleColor
        )
    }

}