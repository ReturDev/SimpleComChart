package github.returdev.simplecomchart.components.goalindicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.TextStyle
import github.returdev.simplecomchart.components.goalindicator.model.GoalIndicatorFixedMeasures


/**
 * A Composable function that creates a goal indicator UI component.
 *
 * @param modifier A Modifier that can be used to adjust the layout or other visual properties of the goal indicator.
 * @param goal The goal to be displayed in the goal indicator.
 * @param goalIndicatorColor The color of the goal indicator.
 * @param onGoalIndicatorColor The color of the text and line on the goal indicator.
 * @param textStyle The style of the text displaying the goal.
 *
 * The goal indicator consists of a Box that displays the goal and a Spacer that draws a line across the width of the parent layout.
 * The Box is clipped to a rounded corner shape and filled with the goalIndicatorColor.
 * The goal is displayed as a Text in the center of the Box, with the color onGoalIndicatorColor and the style textStyle.
 * The Spacer draws a dashed line with the color goalIndicatorColor and the stroke width from the GoalIndicatorFixedMeasures.
 */
@Composable
internal fun GoalIndicator(
    modifier : Modifier = Modifier,
    goal : String,
    goalIndicatorColor : Color,
    onGoalIndicatorColor : Color,
    textStyle : TextStyle
) {

    val measures = GoalIndicatorFixedMeasures

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(measures.goalIndicatorSize)
                .clip(RoundedCornerShape(measures.goalIndicatorCornerRadius))
                .background(goalIndicatorColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = goal,
                color = onGoalIndicatorColor,
                style = textStyle
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        color = goalIndicatorColor,
                        strokeWidth = measures.goalIndicatorStrokeWidth.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f,10f))
                    )
                }
        )

    }

}