package mustafaozhan.github.com.ui.eventdetail

// State
data class EventDetailState(
    val name: String = "",
    val info: String = "",
    val imgUrl: String = "",
    val type: String = "",
    val date: String = "",
    val genre: String = ""
)

// Effect
sealed class EventDetailEffect {
    object BackEffect : EventDetailEffect()
}

// Event
interface EventDetailEvent {
    fun onBackPressed()
}