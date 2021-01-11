package mustafaozhan.github.com.ui.eventdetail

data class EventDetailState(
    val name: String = "",
    val info: String = "",
    val imgUrl: String = "",
    val type: String = "",
    val date: String = "",
    val genre: String = ""
)

sealed class EventDetailEffect {
    object BackEffect : EventDetailEffect()
}

interface EventDetailEvent {
    fun onBackPressed()
}