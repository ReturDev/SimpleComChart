package github.returdev.simplecomchart.components.bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import github.returdev.simplecomchart.components.bar.bubble.SelectedGraphicBarBubble
import github.returdev.simplecomchart.components.bar.model.GraphicBarData
import github.returdev.simplecomchart.components.bar.model.GraphicBarFixedMeasures
import github.returdev.simplecomchart.components.core.extension.noRippleClickable
import github.returdev.simplecomchart.components.core.graph.GraphicBarChartColors


/**
 * This is a Composable function that represents a GraphicBar in a bar chart.
 *
 * @param modifier The modifier to be applied to the GraphicBar. Default is Modifier.
 * @param contentHeightDpPerUnit The height of the content per unit.
 * @param graphicBarData The data representing the graphic bar.
 * @param colors The [GraphicBarChartColors] object that defines the colors for the graphic bar.
 * @param bubbleTextStyle The [TextStyle] object that defines the text style for the bubble content.
 * @param barNameTextStyle The [TextStyle] object that defines the text style for the bar name.
 * @param isSelected A function that returns a boolean indicating whether the bar is selected.
 * @param onClick The callback to be invoked when the bar is clicked.
 */
@Composable
fun GraphicBar(
    modifier : Modifier = Modifier,
    contentHeightDpPerUnit : Dp,
    graphicBarData : GraphicBarData,
    colors : GraphicBarChartColors,
    bubbleTextStyle : TextStyle,
    barNameTextStyle : TextStyle,
    isSelected : Boolean,
    onClick : () -> Unit
) {

    val measures = GraphicBarFixedMeasures

    // Calculate the height of the bar based on the bar value and the height per unit
    val barHeight = remember(graphicBarData) {
        mutableStateOf(
            measures.calculateBarHeight(
                contentHeightDpPerUnit,
                graphicBarData.barValue
            )
        )
    }

    val animatedBarHeight = animateDpAsState(
        animationSpec = tween(500),
        targetValue = barHeight.value,
        label = "bar_height_animation"
    )

//     Animated color of the bar
    val barColor = animateColorAsState(
        targetValue = colors.barColor(selected = isSelected),
        label = "bar_color_animation"
    )

//     Animated color of the bar name
    val barNameColor = animateColorAsState(
        targetValue = colors.barNameColor(selected = isSelected),
        label = "bar_name_color_animation"
    )

    // Create a Column to hold the bar and its bubble
    Column(
        modifier = Modifier
            .requiredWidth(measures.graphicBarRootWidth)
            .fillMaxHeight()
            .padding(0.dp)
            .then(modifier),
        verticalArrangement = Arrangement.Bottom
    ) {

        // Create an AnimatedVisibility that shows or hides the bubble based on whether the bar is selected
        AnimatedVisibility(
            visible = isSelected,
            enter = slideInVertically { it } + fadeIn(animationSpec = tween(500)),
            exit = slideOutVertically(animationSpec = tween(500)) { it } + fadeOut(animationSpec = tween(200))
        ) {

            // Create a SelectedGraphicBarBubble that shows the value of the bar
            SelectedGraphicBarBubble(
                contentText = graphicBarData.barShowValue,
                bubbleColor = colors.barColor(selected = true),
                onBubbleColor = colors.onBarColor(selected = true),
                textStyle = bubbleTextStyle
            )

        }

        // Create a Bar that represents the bar in the bar chart
        Bar(
            barName = graphicBarData.barName,
            barHeight = animatedBarHeight,
            barColor = barColor,
            nameColor = barNameColor,
            textStyle = barNameTextStyle,
            onClick = onClick
        )

    }

}


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