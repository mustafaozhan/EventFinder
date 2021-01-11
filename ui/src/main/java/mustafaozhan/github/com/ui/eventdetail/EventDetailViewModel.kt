package mustafaozhan.github.com.ui.eventdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mustafaozhan.github.com.data.model.Event

class EventDetailViewModel : ViewModel() {

    private var _state = MutableLiveData(EventDetailState())
    var state: LiveData<EventDetailState> = _state

    fun setEvent(event: Event) = with(event) {
        _state.value = _state.value?.copy(
            name = name,
            info = info ?: "",
            imgUrl = images?.get(0)?.url ?: "",
            type = type ?: "",
            date = dates?.start?.localDate ?: ""
        )
    }
}