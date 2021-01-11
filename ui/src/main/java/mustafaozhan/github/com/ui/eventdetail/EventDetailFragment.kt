package mustafaozhan.github.com.ui.eventdetail

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import mustafaozhan.github.com.ui.base.fragment.BaseDBFragment
import mustafaozhan.github.com.ui.databinding.FragmentEventDetailBinding
import javax.inject.Inject

class EventDetailFragment : BaseDBFragment<FragmentEventDetailBinding>() {

    val args: EventDetailFragmentArgs by navArgs()

    @Inject
    lateinit var eventDetailViewModel: EventDetailViewModel

    override fun bind(container: ViewGroup?): FragmentEventDetailBinding =
        FragmentEventDetailBinding.inflate(layoutInflater, container, false)

    override fun onBinding(dataBinding: FragmentEventDetailBinding) {
        binding.vm = eventDetailViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventDetailViewModel.setEvent(args.event)
    }
}