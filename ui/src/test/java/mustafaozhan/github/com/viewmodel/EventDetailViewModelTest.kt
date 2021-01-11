package mustafaozhan.github.com.viewmodel

import io.mockk.MockKAnnotations
import mustafaozhan.github.com.model.Event
import mustafaozhan.github.com.ui.eventdetail.EventDetailEffect
import mustafaozhan.github.com.ui.eventdetail.EventDetailViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EventDetailViewModelTest : BaseViewModelTest<EventDetailViewModel>() {

    override lateinit var viewModel: EventDetailViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = EventDetailViewModel()
    }


    @Test
    fun setEvent() {
        val mockEvent = Event(
            id = "id",
            name = "name",
            info = "info",
            imgUrl = "imgUrl",
            type = "type",
            date = "date",
            genre = "genre",
        )

        viewModel.setEvent(mockEvent)

        Assert.assertEquals(mockEvent.name, viewModel.state.value?.name)
        Assert.assertEquals(mockEvent.name, viewModel.state.value?.name)
        Assert.assertEquals(mockEvent.info, viewModel.state.value?.info)
        Assert.assertEquals(mockEvent.imgUrl, viewModel.state.value?.imgUrl)
        Assert.assertEquals(mockEvent.type, viewModel.state.value?.type)
        Assert.assertEquals(mockEvent.date, viewModel.state.value?.date)
    }

    // event
    @Test
    fun onBackPressed() = with(viewModel) {
        getEvent().onBackPressed()
        Assert.assertEquals(EventDetailEffect.BackEffect, effect.value)
    }
}