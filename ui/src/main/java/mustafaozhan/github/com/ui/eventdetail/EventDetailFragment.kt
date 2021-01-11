package mustafaozhan.github.com.ui.eventdetail

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import mustafaozhan.github.com.ui.base.fragment.BaseDBFragment
import mustafaozhan.github.com.ui.databinding.FragmentEventDetailBinding
import mustafaozhan.github.com.util.reObserve
import javax.inject.Inject

class EventDetailFragment : BaseDBFragment<FragmentEventDetailBinding>() {

    private val args: EventDetailFragmentArgs by navArgs()

    @Inject
    lateinit var eventDetailViewModel: EventDetailViewModel

    override fun bind(container: ViewGroup?): FragmentEventDetailBinding =
        FragmentEventDetailBinding.inflate(layoutInflater, container, false)

    override fun onBinding(dataBinding: FragmentEventDetailBinding) {
        binding.vm = eventDetailViewModel
        binding.event = eventDetailViewModel.getEvent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventDetailViewModel.setEvent(args.event)
        observeEffects()
    }

    private fun observeEffects() {
        eventDetailViewModel.effect.reObserve(viewLifecycleOwner, { eventDetailEffect ->
            when (eventDetailEffect) {
                is EventDetailEffect.BackEffect -> requireActivity().onBackPressed()
            }
        })
    }
}