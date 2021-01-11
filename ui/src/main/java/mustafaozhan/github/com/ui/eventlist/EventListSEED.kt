package mustafaozhan.github.com.ui.eventlist

import mustafaozhan.github.com.model.Event
import mustafaozhan.github.com.model.Page

data class EventListState(
    val eventList: MutableList<Event> = mutableListOf(),
    val isLoading: Boolean = true,
    val pageNumber: Int = 0,
)

sealed class EventListEffect {
    data class OpenEventDetail(val event: Event) : EventListEffect()
}

interface EventListEvent {
    fun favoriteClicked(item: Event)
    fun endOfPageEvent()
    fun openEventDetail(item: Event)
}

data class EventListData(
    var unFilteredList: MutableList<Event>? = mutableListOf(),
    var query: String = "",
    var page: Page? = null
)