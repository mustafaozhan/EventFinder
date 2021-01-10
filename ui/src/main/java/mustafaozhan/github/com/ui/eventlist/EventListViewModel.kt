package mustafaozhan.github.com.ui.eventlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mustafaozhan.github.com.data.api.ApiRepository
import timber.log.Timber

class EventListViewModel(
    private val apiRepository: ApiRepository
) : ViewModel() {
    var helloWorld = "Hello World!"

    init {
        viewModelScope.launch {
            apiRepository.getEvents().execute({
                Timber.w(it.toString())
            }, {
                Timber.e(it)
            })
        }
    }
}