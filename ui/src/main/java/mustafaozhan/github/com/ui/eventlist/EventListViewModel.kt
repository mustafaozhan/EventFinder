package mustafaozhan.github.com.ui.eventlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mustafaozhan.github.com.data.api.ApiRepository
import timber.log.Timber

class EventListViewModel(
    private val apiRepository: ApiRepository
) : ViewModel() {

    private var _state = MutableLiveData(EventListState())
    var state: LiveData<EventListState> = _state

    init {
        viewModelScope.launch {
            apiRepository.getEvents().execute({
                _state.value = _state.value?.copy(
                    eventList = it.embedded.events,
                    isLoading = false
                )
            }, {
                _state.value = _state.value?.copy(isLoading = false)
                Timber.e(it)
            })
        }
    }
}