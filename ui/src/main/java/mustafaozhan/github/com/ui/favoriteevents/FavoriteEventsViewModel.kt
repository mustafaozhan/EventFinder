package mustafaozhan.github.com.ui.favoriteevents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import mustafaozhan.github.com.db.EventDao
import mustafaozhan.github.com.model.Event
import mustafaozhan.github.com.model.toEntity
import mustafaozhan.github.com.model.toModel
import mustafaozhan.github.com.util.MutableSingleLiveData
import mustafaozhan.github.com.util.SingleLiveData

class FavoriteEventsViewModel(
    private val eventDao: EventDao
) : ViewModel(), FavoriteEventsEvent {

    // region SEED
    private var _state = MutableLiveData(FavoriteEventsState())
    var state: LiveData<FavoriteEventsState> = _state

    private var _effect = MutableSingleLiveData<FavoriteEventsEffect>()
    var effect: SingleLiveData<FavoriteEventsEffect> = _effect

    fun getEvent() = this as FavoriteEventsEvent
    // endregion

    init {
        _state.value = _state.value?.copy(loading = true)
        viewModelScope.launch {
            eventDao.collectFavoriteEvents()
                .map { it.map { it.toModel() } }
                .collect {
                    _state.value = _state.value?.copy(
                        eventList = it.filter { it.isFavorite },
                        loading = false
                    )
                }
        }
    }

    // region events
    override fun favoriteClicked(item: Event) {
        viewModelScope.launch {
            item.toEntity().let {
                eventDao.insertEvent(it.copy(isFavorite = !it.isFavorite))
            }
        }
    }

    override fun openEventDetail(item: Event) {
        _effect.postValue(FavoriteEventsEffect.OpenEventDetail(item))
    }

    override fun onBackPressed() {
        _effect.postValue(FavoriteEventsEffect.BackEffect)
    }
    // endregion
}