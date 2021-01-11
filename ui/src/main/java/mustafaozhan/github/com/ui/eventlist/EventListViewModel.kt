package mustafaozhan.github.com.ui.eventlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mustafaozhan.github.com.data.api.ApiRepository
import mustafaozhan.github.com.data.db.EventDao
import mustafaozhan.github.com.data.model.Event
import mustafaozhan.github.com.data.model.Event.Companion.toEntity
import mustafaozhan.github.com.data.model.EventsResponse
import mustafaozhan.github.com.util.MutableSingleLiveData
import mustafaozhan.github.com.util.SingleLiveData
import timber.log.Timber

class EventListViewModel(
    private val apiRepository: ApiRepository,
    private val eventDao: EventDao
) : ViewModel(), EventListEvent {

    // region SEED
    private var _state = MutableLiveData(EventListState())
    var state: LiveData<EventListState> = _state

    private var _effect = MutableSingleLiveData<EventListEffect>()
    var effect: SingleLiveData<EventListEffect> = _effect

    fun getEvent() = this as EventListEvent

    var data = EventListData()
    // endregion

    init {
        viewModelScope.launch {
            getEventsFromApi()
            getFavoriteEvents()
        }
    }

    private suspend fun getEventsFromApi(pageNumber: Long = 0) {
        _state.value = _state.value?.copy(isLoading = true)

        apiRepository.getEvents(pageNumber)
            .execute(::eventsDownloadSuccess, ::eventsDownloadFailed) {
                _state.value = _state.value?.copy(isLoading = false)
            }
    }

    private fun eventsDownloadSuccess(eventsResponse: EventsResponse) {
        _state.value?.eventList?.let {
            _state.value = _state.value?.copy(
                eventList = it.toMutableList().apply {
                    addAll(eventsResponse.embedded.events)
                }
            )
        } ?: run {
            _state.value = _state.value?.copy(
                eventList = eventsResponse.embedded.events.toMutableList()
            )
        }

        data.unFilteredList = eventsResponse.embedded.events.toMutableList()
        data.page = eventsResponse.page
    }

    private fun eventsDownloadFailed(throwable: Throwable) {
        Timber.e(throwable)
    }

    private suspend fun getFavoriteEvents() = eventDao.collectFavoriteEvents()
        .collect { databaseEvents ->
            state.value?.eventList?.let {
                val tempList = it.toMutableList()

                databaseEvents.forEach { eventEntity ->
                    tempList.forEachIndexed { index, event ->
                        if (event.id == eventEntity.id) {
                            tempList[index].isFavorite = eventEntity.isFavorite
                        }
                    }
                }

                _state.value = _state.value?.copy(eventList = tempList)
            }
        }

    fun filterList(txt: String) = data.unFilteredList
        ?.filter {
            it.name.contains(txt, true)
        }?.toMutableList()
        ?.let {
            _state.value = _state.value?.copy(
                eventList = it,
                isLoading = false
            )
        }.run {
            data.query = txt
            true
        }

    // region events
    override fun favoriteClicked(item: Event) {
        viewModelScope.launch {
            item.toEntity().let {
                eventDao.insertEvent(it.copy(isFavorite = !it.isFavorite))
            }
        }
    }

    override fun endOfPageEvent() {
        viewModelScope.launch {
            if (data.page?.number ?: 0 < data.page?.totalPages ?: 0)
                getEventsFromApi((data.page?.number ?: 0) + 1)
        }
    }

    override fun openEventDetail(item: Event) {
        _effect.postValue(EventListEffect.OpenEventDetail(item))
    }
    // endregion
}