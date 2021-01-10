package mustafaozhan.github.com.eventfinder.ui.eventlist

import android.view.ViewGroup
import mustafaozhan.github.com.eventfinder.base.fragment.BaseDBFragment
import mustafaozhan.github.com.eventfinder.databinding.FragmentEventListBinding
import javax.inject.Inject

class EventListFragment : BaseDBFragment<FragmentEventListBinding>() {

    @Inject
    lateinit var eventListViewModel: EventListViewModel

    override fun bind(container: ViewGroup?): FragmentEventListBinding =
        FragmentEventListBinding.inflate(layoutInflater, container, false)

    override fun onBinding(dataBinding: FragmentEventListBinding) {
        binding.vm = eventListViewModel
    }

}