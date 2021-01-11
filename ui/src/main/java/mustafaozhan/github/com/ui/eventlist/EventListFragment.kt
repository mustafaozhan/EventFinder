package mustafaozhan.github.com.ui.eventlist

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mustafaozhan.github.com.ui.R
import mustafaozhan.github.com.ui.base.fragment.BaseDBFragment
import mustafaozhan.github.com.ui.databinding.FragmentEventListBinding
import mustafaozhan.github.com.util.reObserve
import javax.inject.Inject

class EventListFragment : BaseDBFragment<FragmentEventListBinding>() {

    @Inject
    lateinit var eventListViewModel: EventListViewModel

    private lateinit var eventListAdapter: EventListAdapter

    override fun bind(container: ViewGroup?): FragmentEventListBinding =
        FragmentEventListBinding.inflate(layoutInflater, container, false)

    override fun onBinding(dataBinding: FragmentEventListBinding) {
        binding.vm = eventListViewModel
        eventListAdapter = EventListAdapter(eventListViewModel.getEvent())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeEffects()
    }

    private fun observeEffects() {
        eventListViewModel.effect.reObserve(viewLifecycleOwner, { eventListEffect ->
            when (eventListEffect) {
                is EventListEffect.OpenEventDetail -> navigate(
                    R.id.eventListFragment,
                    EventListFragmentDirections.actionEventListFragmentToEventDetailFragment(
                        eventListEffect.event
                    )
                )
            }
        })
    }

    private fun initView() {
        binding.eventListRecyclerview.adapter = eventListAdapter
        binding.eventListRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    eventListViewModel.getEvent().endOfPageEvent()
                }
            }
        })

        with(eventListViewModel) {
            state.observe(viewLifecycleOwner, {
                eventListAdapter.submitList(it.eventList)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        eventListViewModel.filterList("")
    }

}