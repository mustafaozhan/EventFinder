package mustafaozhan.github.com.ui.eventlist

import mustafaozhan.github.com.data.model.Event

data class EventListState(
    var eventList: List<Event> = listOf(),
    var isLoading: Boolean = true
)