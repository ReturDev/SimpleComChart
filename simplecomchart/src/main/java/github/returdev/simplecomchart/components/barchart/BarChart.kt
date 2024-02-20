package github.returdev.simplecomchart.components.barchart

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import github.returdev.simplecomchart.components.bar.GraphicBar
import github.returdev.simplecomchart.components.bar.model.GraphicBarData
import github.returdev.simplecomchart.core.model.graph.BarChartColors
import github.returdev.simplecomchart.core.model.wrapper.ListWrapper


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