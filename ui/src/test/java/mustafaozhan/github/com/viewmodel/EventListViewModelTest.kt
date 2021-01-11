package mustafaozhan.github.com.viewmodel

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import mustafaozhan.github.com.api.ApiRepository
import mustafaozhan.github.com.db.EventDao
import mustafaozhan.github.com.model.*
import mustafaozhan.github.com.ui.eventlist.EventListEffect
import mustafaozhan.github.com.ui.eventlist.EventListViewModel
import mustafaozhan.github.com.util.Result
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EventListViewModelTest : BaseViewModelTest<EventListViewModel>() {

    override lateinit var viewModel: EventListViewModel

    @RelaxedMockK
    lateinit var apiRepository: ApiRepository

    @RelaxedMockK
    lateinit var eventDao: EventDao

    private val mockPage = Page(0, 1, 2, 3)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        coEvery { apiRepository.getEvents(0) } returns Result.Success(
            EventsResponse(
                Embedded(listOf()),
                Links(),
                mockPage
            )
        )
        viewModel = EventListViewModel(apiRepository, eventDao)
    }

    @Test
    fun filterList() {
        val mockEvent1 = Event("id", "name")
        val mockEvent2 = Event(
            "id",
            "name",
            classifications = listOf(Classification(genre = Genre(name = "genre")))
        )

        viewModel.data.unFilteredList = mutableListOf<Event>().apply {
            add(mockEvent1)
            add(mockEvent2)
        }

        viewModel.filterList("na")
        assertEquals(true, viewModel.state.value?.eventList?.contains(mockEvent1))

        viewModel.filterList("ge")
        assertEquals(true, viewModel.state.value?.eventList?.contains(mockEvent2))
    }

    // event
    @Test

    fun endOfPageEvent() {
        viewModel.getEvent().endOfPageEvent()
        assertEquals(mockPage, viewModel.data.page)
    }

    @Test
    fun openEventDetail() {
        val mockEvent = Event("id", "name")
        viewModel.getEvent().openEventDetail(mockEvent)
        assertEquals(EventListEffect.OpenEventDetail(mockEvent), viewModel.effect.value)
    }

    @Test
    fun openFavoriteEvents() {
        viewModel.getEvent().openFavoriteEvents()
        assertEquals(EventListEffect.OpenFavoriteEvents, viewModel.effect.value)
    }
}