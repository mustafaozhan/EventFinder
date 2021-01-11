package mustafaozhan.github.com.ui.favoriteevents

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import mustafaozhan.github.com.base.fragment.BaseDBFragment
import mustafaozhan.github.com.ui.R
import mustafaozhan.github.com.ui.databinding.FragmentFavoriteEventsBinding
import mustafaozhan.github.com.util.reObserve
import javax.inject.Inject

class FavoriteEventsFragment : BaseDBFragment<FragmentFavoriteEventsBinding>() {

    @Inject
    lateinit var favoriteEventsViewModel: FavoriteEventsViewModel

    private lateinit var favoriteEventsAdapter: FavoriteEventsAdapter

    override fun bind(container: ViewGroup?): FragmentFavoriteEventsBinding =
        FragmentFavoriteEventsBinding.inflate(layoutInflater, container, false)

    override fun onBinding(dataBinding: FragmentFavoriteEventsBinding) {
        binding.vm = favoriteEventsViewModel
        favoriteEventsViewModel.getEvent().let {
            binding.event = it
            favoriteEventsAdapter = FavoriteEventsAdapter(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeEffects()
    }

    private fun observeEffects() {
        favoriteEventsViewModel.effect.reObserve(viewLifecycleOwner, { eventListEffect ->
            when (eventListEffect) {
                is FavoriteEventsEffect.OpenEventDetail -> navigate(
                    R.id.favoriteEventsFragment,
                    FavoriteEventsFragmentDirections.actionFavoriteEventsFragmentToEventDetailFragment(
                        eventListEffect.event
                    )
                )
                is FavoriteEventsEffect.BackEffect -> requireActivity().onBackPressed()
            }
        })
    }

    private fun initView() {
        binding.eventListRecyclerview.adapter = favoriteEventsAdapter

        with(favoriteEventsViewModel) {
            state.observe(viewLifecycleOwner, {
                favoriteEventsAdapter.submitList(it.eventList)
            })
        }
    }
}
