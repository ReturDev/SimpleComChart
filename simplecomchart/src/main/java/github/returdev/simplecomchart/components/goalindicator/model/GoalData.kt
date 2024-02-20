package github.returdev.simplecomchart.components.goalindicator.model

import androidx.compose.runtime.Immutable

/**
 * Immutable data class representing goal data for the chart.
 *
 * @property goalValue The numerical value of the goal.
 * @property goalShowValue The string representation of the goal value.
 */
@Immutable
data class GoalData(
    val goalValue : Float,
    val goalShowValue : String
)
