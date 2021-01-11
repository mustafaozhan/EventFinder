package mustafaozhan.github.com.ui.eventlist

import mustafaozhan.github.com.data.model.Event
import mustafaozhan.github.com.data.model.Page

data class EventListState(
    var eventList: MutableList<Event> = mutableListOf(),
    var isLoading: Boolean = true,
    var pageNumber: Int = 0,
)

interface EventListEvent {
    fun favoriteClicked(item: Event)
    fun endOfPageEvent()
}

data class EventListData(
    var unFilteredList: MutableList<Event>? = mutableListOf(),
    var query: String = "",
    var page: Page? = null
)