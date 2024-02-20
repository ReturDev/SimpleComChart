package github.returdev.simplecomchart.components.bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import github.returdev.simplecomchart.components.bar.model.GraphicBarFixedMeasures
import github.returdev.simplecomchart.components.core.extension.noRippleClickable


/**
 * This is a Composable function that creates a Bar.
 * The Bar consists of a graphic bar and its name.
 * The graphic bar's width and height are determined by the GraphicBarFixedMeasures and the barHeight parameter.
 * The graphic bar's color is determined by the barColor parameter.
 * The bar's name is displayed below the graphic bar.
 * The bar's name's color is determined by the nameColor parameter.
 * The bar's name's text style is determined by the textStyle parameter.
 * When the bar is clicked, the onClick function is called.
 *
 * @param modifier The Modifier to be applied to the Bar. Default is Modifier.
 * @param barName The name of the bar. It is displayed below the graphic bar.
 * @param barHeight The height of the graphic bar. It is a State of Dp.
 * @param barColor The color of the graphic bar. It is a State of Color.
 * @param nameColor The color of the bar's name. It is a State of Color.
 * @param textStyle The text style of the bar's name.
 * @param onClick The function to be called when the bar is clicked.
 */
@Composable
internal fun Bar(
    modifier: Modifier = Modifier,
    barName: String,
    barHeight: State<Dp>,
    barColor : State<Color>,
    nameColor : State<Color>,
    textStyle: TextStyle,
    onClick : () -> Unit
) {

    val measures = GraphicBarFixedMeasures

    // Column to hold the bar and its name
    Column(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Graphic bar
        Box(
            modifier = Modifier
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        constraints.copy(
                            minWidth = measures.graphicBarWidth
                                .toPx()
                                .toInt(),
                            minHeight = barHeight.value
                                .toPx()
                                .toInt(),
                        )
                    )
                    layout(placeable.width, placeable.height) {
                        placeable.place(0, 0)
                    }
                }
                .clip(measures.graphicBarRoundedCornerShape)
                .drawBehind {
                    drawRect(barColor.value)
                },
        )

        // Bar name
        Text(
            modifier = Modifier
                .height(measures.graphicTextHeight)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .drawWithContent {
                    drawContent()
                    drawRect(
                        color = nameColor.value,
                        blendMode = BlendMode.SrcIn
                    )
                },
            text = barName,
            style = textStyle
        )

    }

}