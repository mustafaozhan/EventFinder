package mustafaozhan.github.com.ui.eventlist

import mustafaozhan.github.com.data.model.Event

data class EventListState(
    var eventList: List<Event> = listOf(),
    var isLoading: Boolean = true
)

interface EventListEvent {
    fun favoriteClicked(item: Event)
}

data class EventListData(
    var unFilteredList: MutableList<Event>? = mutableListOf(),
    var query: String = ""
)