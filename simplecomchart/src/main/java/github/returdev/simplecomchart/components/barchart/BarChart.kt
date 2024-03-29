package github.returdev.simplecomchart.components.barchart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import github.returdev.simplecomchart.components.bar.GraphicBar
import github.returdev.simplecomchart.components.bar.model.GraphicBarData
import github.returdev.simplecomchart.components.barchart.model.BarChartFixedMeasures
import github.returdev.simplecomchart.components.goalindicator.GoalIndicator
import github.returdev.simplecomchart.components.goalindicator.model.GoalData
import github.returdev.simplecomchart.components.unit.BarChartUnitText
import github.returdev.simplecomchart.core.extension.pixelsToDp
import github.returdev.simplecomchart.core.model.graph.BarChartColors
import github.returdev.simplecomchart.core.model.graph.BarChartTextStyles
import github.returdev.simplecomchart.core.model.graph.BarChartDefaults
import github.returdev.simplecomchart.core.model.wrapper.ListWrapper


/**
 * A Composable function that creates a Bar Chart.
 *
 * @param modifier The modifier to be applied to the Bar Chart.
 * @param height The height of the Bar Chart.
 * @param maxGraphValue The maximum value of the graph.
 * @param maxGraphShowValue The maximum value of the graph to be shown.
 * @param unit The unit of measurement for the graph.
 * @param goal The goal data for the graph.
 * @param barData The data for each bar in the chart.
 * @param firstBarDataSelectedId The id of the first selected bar data.
 * @param colors The colors used in the bar chart.
 * @param textStyles The text styles used in the bar chart.
 *
 * The selected GraphicBar is the one with the id that matches the barSelectedId.
 * When a GraphicBar is clicked, the barSelectedId is updated to its id.
 */
@ExperimentalComposeUiApi
@Composable
fun BarChart(
    modifier : Modifier = Modifier,
    height : Dp,
    maxGraphValue : Float,
    maxGraphShowValue : String,
    unit : String,
    goal : GoalData?,
    barData : ListWrapper<GraphicBarData>,
    firstBarDataSelectedId : Int,
    colors : BarChartColors,
    textStyles : BarChartTextStyles = BarChartDefaults.graphicBarChartTextStyles()
) {

    val measures = BarChartFixedMeasures

    val totalContentHeight = remember(height) { measures.calculateTotalContentHeight(height) }
    val contentHeightPerDp = remember(maxGraphValue, totalContentHeight) { totalContentHeight / maxGraphValue }

    var barSelected by remember { mutableStateOf(firstBarDataSelectedId) }


    val onBackgroundColor = colors.onBackgroundColor


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(measures.backgroundCornerSize))
            .background(colors.backgroundColor)
            .then(modifier)
    ){

        GraphicBarContentLimitDraws(
            maximumGraphValue = maxGraphValue,
            maximumGraphShowValue = maxGraphShowValue,
            onBackgroundColor = onBackgroundColor,
            textStyle = textStyles.graphicValueIndicatorStyle
        )

        goal?.let {

            GoalIndicator(
                modifier = Modifier.offset(
                    x = 0.dp,
                    y = measures.goalIndicatorMeasures.calculateIndicatorYAxisPosition(
                        totalHeight = totalContentHeight,
                        topContentPadding = measures.topContentPadding,
                        contentHeightDpPerUnit = contentHeightPerDp,
                        goalValue = it.goalValue
                    )
                ),
                goal = it.goalShowValue,
                goalIndicatorColor = colors.goalIndicatorColor,
                onGoalIndicatorColor = colors.onGoalIndicatorColor,
                textStyle = textStyles.goalIndicatorStyle
            )

        }

        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            BarChartUnitText(
                modifier = Modifier
                    .size(
                        width = measures.yAxisLinesStartPadding + measures.startYAxisSpace,
                        height = measures.graphicBarMeasures.graphicTextHeight
                    )
                    .align(Alignment.Bottom),
                unit = unit,
                textColor = colors.onBackgroundColor,
                textStyle = textStyles.graphicUnitStyle
            )

            GraphicBarsContainer(
                barDataList = barData,
                contentHeightDpPerUnit = contentHeightPerDp,
                colors = colors,
                bubbleTextStyle = textStyles.barChartBubbleStyle,
                barNameTextStyle = textStyles.barChartNameStyle,
                barSelectedId = barSelected,
                onBarSelected = { barSelected = it }
            )

        }

    }

}

/**
 * A Composable function that creates a UI component to display the content limit draws for a graphic bar in a bar chart.
 *
 * @param maximumGraphValue The maximum value of the graph.
 * @param maximumGraphShowValue The maximum value of the graph to be shown.
 * @param onBackgroundColor The color of the background.
 * @param textStyle The style of the text.
 */
@OptIn(ExperimentalTextApi::class)
@Composable
internal fun GraphicBarContentLimitDraws(
    maximumGraphValue : Float,
    maximumGraphShowValue : String,
    onBackgroundColor : Color,
    textStyle : TextStyle
){

    val measures = BarChartFixedMeasures

    val textMeasurer = rememberTextMeasurer()

    val textLayoutResult = remember(maximumGraphValue) {
        textMeasurer.measure(maximumGraphShowValue, textStyle)
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = measures.calculateTopContentDrawsPadding(
                    textLayoutResult.size.height.pixelsToDp()
                )
            )
    ){

        drawLine(
            color = onBackgroundColor.copy(alpha = 0.5f),
            start = Offset(
                x = measures.yAxisLinesStartPadding.toPx() + measures.startYAxisSpace.toPx(),
                y = 0f + textLayoutResult.size.height / 2
            ),
            end = Offset(
                x = size.width,
                y = 0f + textLayoutResult.size.height / 2
            )
        )

        drawText(
            textMeasurer = textMeasurer,
            text = maximumGraphShowValue,
            topLeft = Offset(
                x = (measures.startYAxisSpace.toPx() - textLayoutResult.size.width) / 2,
                y = 0f
            ),
            style = textStyle.copy(color = onBackgroundColor)
        )

        drawLine(
            color = onBackgroundColor.copy(alpha = 0.5f),
            start = Offset(
                x = measures.yAxisLinesStartPadding.toPx() + measures.startYAxisSpace.toPx(),
                y = size.height - measures.graphicBarMeasures.graphicTextHeight.toPx()
            ),
            end = Offset(
                x = size.width,
                y = size.height - measures.bottomContentPadding.toPx()
            )
        )
    }

}

/**
 * A Composable function that creates a container for graphic bars in a bar chart.
 *
 * @param barDataList A ListWrapper of GraphicBarData, which holds the data for each bar in the chart.
 * @param contentHeightDpPerUnit The height in Dp for each unit of content in the bars.
 * @param colors The colors used in the bar chart.
 * @param bubbleTextStyle The text style for the bubble text in the bars.
 * @param barNameTextStyle The text style for the name of the bars.
 * @param barSelectedId The id of the selected bar.
 * @param onBarSelected A function that is called when a bar is selected.
 *
 */
@Composable
internal fun GraphicBarsContainer(
    barDataList : ListWrapper<GraphicBarData>,
    contentHeightDpPerUnit : Dp,
    colors : BarChartColors,
    bubbleTextStyle : TextStyle,
    barNameTextStyle : TextStyle,
    barSelectedId : Int,
    onBarSelected : (Int) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        barDataList.list.forEach {

            GraphicBar(
                contentHeightDpPerUnit = contentHeightDpPerUnit,
                graphicBarData = it,
                colors = colors,
                bubbleTextStyle = bubbleTextStyle,
                barNameTextStyle = barNameTextStyle,
                isSelected = barSelectedId == it.id ,
                onClick = {
                    onBarSelected(it.id)
                }
            )

        }

    }

}