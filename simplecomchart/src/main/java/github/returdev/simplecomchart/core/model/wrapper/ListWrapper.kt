package github.returdev.simplecomchart.core.model.wrapper

import androidx.compose.runtime.Stable

/**
 * A data class that wraps a list of a generic type.
 *
 * @param T The type of elements in the list.
 * @property list The list of elements of type T.
 *
 * This class is marked as @Stable, which means that changes to its properties will be observed by Compose.
 */
@Stable
data class ListWrapper<T>(
    val list : List<T>
)
