package github.returdev.simplecomchart.components.bar.bubble

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import github.returdev.simplecomchart.components.bar.bubble.model.BubbleFixedMeasures

/**
 * Composable function representing a selected graphic bar bubble.
 *
 * @param contentText The text content to be displayed inside the bubble.
 * @param bubbleColor The background color of the bubble.
 * @param onBubbleColor The color of the text content inside the bubble.
 * @param textStyle The [TextStyle] defining the text style for the content inside the bubble.
 */
@Composable
internal fun SelectedGraphicBarBubble(
    contentText : String,
    bubbleColor : Color,
    onBubbleColor : Color,
    textStyle : TextStyle
) {

    val measures = BubbleFixedMeasures

    Column(
        modifier = Modifier.padding(bottom = measures.bubbleBottomPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Bubble(
            text = contentText,
            bubbleSize = measures.bubbleSize,
            bubbleRoundedCornerShape = measures.bubbleRoundedCornerShape,
            bubbleColor = bubbleColor,
            onBubbleColor = onBubbleColor,
            textStyle = textStyle
        )

        BubbleTail(
            bubbleTailSize = measures.bubbleTailSize,
            bubbleColor = bubbleColor
        )

    }

}

/**
 * Composable function representing the bubble part of the selected graphic bar bubble.
 *
 * @param text The text content to be displayed inside the bubble.
 * @param bubbleSize The size of the bubble.
 * @param bubbleRoundedCornerShape The rounded corner shape of the bubble.
 * @param bubbleColor The background color of the bubble.
 * @param onBubbleColor The color of the text content inside the bubble.
 * @param textStyle The [TextStyle] defining the text style for the content inside the bubble.
 */
@Composable
private fun Bubble(
    text : String,
    bubbleSize: DpSize,
    bubbleRoundedCornerShape : RoundedCornerShape,
    bubbleColor : Color,
    onBubbleColor : Color,
    textStyle : TextStyle
) {

    Box(
        modifier = Modifier
            .size(bubbleSize)
            .clip(bubbleRoundedCornerShape)
            .background(bubbleColor),
        contentAlignment = Alignment.Center
    ){

        Text(
            text = text,
            color = onBubbleColor,
            style = textStyle
        )

    }

}

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