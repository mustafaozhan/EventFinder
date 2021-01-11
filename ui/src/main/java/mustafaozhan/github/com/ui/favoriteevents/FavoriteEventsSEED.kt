package mustafaozhan.github.com.ui.favoriteevents

import mustafaozhan.github.com.model.Event

// State
data class FavoriteEventsState(
    val eventList: List<Event> = listOf(),
    val loading: Boolean = true
)

// Effect
sealed class FavoriteEventsEffect {
    data class OpenEventDetail(val event: Event) : FavoriteEventsEffect()
    object BackEffect : FavoriteEventsEffect()
}

// Event
interface FavoriteEventsEvent {
    fun favoriteClicked(item: Event)
    fun openEventDetail(item: Event)
    fun onBackPressed()
}