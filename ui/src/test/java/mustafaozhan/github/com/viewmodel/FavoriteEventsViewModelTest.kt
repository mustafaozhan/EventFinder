package mustafaozhan.github.com.viewmodel

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import mustafaozhan.github.com.db.EventDao
import mustafaozhan.github.com.model.Event
import mustafaozhan.github.com.ui.favoriteevents.FavoriteEventsEffect
import mustafaozhan.github.com.ui.favoriteevents.FavoriteEventsViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FavoriteEventsViewModelTest : BaseViewModelTest<FavoriteEventsViewModel>() {

    override lateinit var viewModel: FavoriteEventsViewModel

    @RelaxedMockK
    lateinit var eventDao: EventDao

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = FavoriteEventsViewModel(eventDao)
    }

    // Event
    @Test
    fun onBackPressed() = with(viewModel) {
        getEvent().onBackPressed()
        assertEquals(FavoriteEventsEffect.BackEffect, effect.value)
    }

    @Test
    fun openEventDetail() = with(viewModel) {
        val mockEvent = Event("id", "name")
        getEvent().openEventDetail(mockEvent)
        assertEquals(FavoriteEventsEffect.OpenEventDetail(mockEvent), effect.value)
    }
}