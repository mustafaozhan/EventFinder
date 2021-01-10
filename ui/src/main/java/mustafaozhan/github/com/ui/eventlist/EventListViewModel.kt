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
import timber.log.Timber

class EventListViewModel(
    private val apiRepository: ApiRepository,
    private val eventDao: EventDao
) : ViewModel(), EventListEvent {

    private var _state = MutableLiveData(EventListState())
    var state: LiveData<EventListState> = _state

    fun getEvent() = this as EventListEvent

    var data = EventListData()

    init {
        viewModelScope.launch {
            getEventsFromApi()
            getFavoriteEvents()
        }
    }

    private suspend fun getEventsFromApi() = apiRepository.getEvents().execute({
        _state.value = _state.value?.copy(
            eventList = it.embedded.events,
            isLoading = false
        )
        data.unFilteredList = it.embedded.events.toMutableList()
    }, {
        _state.value = _state.value?.copy(isLoading = false)
        Timber.e(it)
    })

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

    override fun favoriteClicked(item: Event) {
        viewModelScope.launch {
            item.toEntity().let {
                eventDao.insertEvent(it.copy(isFavorite = !it.isFavorite))
            }
        }
    }
}