package mustafaozhan.github.com.ui.eventdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mustafaozhan.github.com.model.Event
import mustafaozhan.github.com.util.MutableSingleLiveData
import mustafaozhan.github.com.util.SingleLiveData

class EventDetailViewModel : ViewModel(), EventDetailEvent {

    // region SEED
    private var _state = MutableLiveData(EventDetailState())
    var state: LiveData<EventDetailState> = _state

    fun getEvent() = this as EventDetailEvent

    private var _effect = MutableSingleLiveData<EventDetailEffect>()
    var effect: SingleLiveData<EventDetailEffect> = _effect
    // endregion

    fun setEvent(event: Event) = with(event) {
        _state.value = _state.value?.copy(
            name = name,
            info = info ?: "",
            imgUrl = images?.get(0)?.url ?: "",
            type = type ?: "",
            date = dates?.start?.localDate ?: "",
            genre = classifications?.get(0)?.genre?.name ?: ""
        )
    }

    // region events
    override fun onBackPressed() {
        _effect.postValue(EventDetailEffect.BackEffect)
    }
    // endregion
}