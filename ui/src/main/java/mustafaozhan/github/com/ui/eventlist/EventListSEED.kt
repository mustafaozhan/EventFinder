package mustafaozhan.github.com.ui.eventlist

import mustafaozhan.github.com.model.Event
import mustafaozhan.github.com.model.Page

// State
data class EventListState(
    val eventList: MutableList<Event> = mutableListOf(),
    val isLoading: Boolean = true,
    val pageNumber: Int = 0,
    val favoriteItemCount: Int = 0
)

// Effect
sealed class EventListEffect {
    data class OpenEventDetail(val event: Event) : EventListEffect()
    object OpenFavoriteEvents : EventListEffect()
}

// Event
interface EventListEvent {
    fun favoriteClicked(item: Event)
    fun endOfPageEvent()
    fun openEventDetail(item: Event)
    fun openFavoriteEvents()
}

// Data
data class EventListData(
    var unFilteredList: MutableList<Event>? = mutableListOf(),
    var query: String = "",
    var page: Page? = null
)