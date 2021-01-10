package mustafaozhan.github.com.ui.eventlist

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import mustafaozhan.github.com.ui.base.fragment.BaseDBFragment
import mustafaozhan.github.com.ui.databinding.FragmentEventListBinding
import mustafaozhan.github.com.util.reObserve
import javax.inject.Inject

class EventListFragment : BaseDBFragment<FragmentEventListBinding>() {

    @Inject
    lateinit var eventListViewModel: EventListViewModel

    private var eventListAdapter: EventListAdapter = EventListAdapter()

    override fun bind(container: ViewGroup?): FragmentEventListBinding =
        FragmentEventListBinding.inflate(layoutInflater, container, false)

    override fun onBinding(dataBinding: FragmentEventListBinding) {
        binding.vm = eventListViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.eventListRecyclerview.adapter = eventListAdapter

        with(eventListViewModel) {
            state.reObserve(viewLifecycleOwner, {
                eventListAdapter.submitList(it.eventList)
            })
        }
    }

}