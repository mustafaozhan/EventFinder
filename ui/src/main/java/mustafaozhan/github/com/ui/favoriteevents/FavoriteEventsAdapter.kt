package mustafaozhan.github.com.ui.favoriteevents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import mustafaozhan.github.com.base.BaseDBRecyclerViewAdapter
import mustafaozhan.github.com.model.Event
import mustafaozhan.github.com.ui.databinding.ItemFavoriteEventsBinding

class FavoriteEventsAdapter(
    private val favoriteEventsEvent: FavoriteEventsEvent
) : BaseDBRecyclerViewAdapter<Event, ItemFavoriteEventsBinding>(CalculatorDiffer()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CalculatorDBViewHolder(
        ItemFavoriteEventsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class CalculatorDBViewHolder(itemBinding: ItemFavoriteEventsBinding) :
        BaseDBViewHolder<Event, ItemFavoriteEventsBinding>(itemBinding) {

        override fun onItemBind(item: Event) = with(itemBinding) {
            this.item = item
            this.event = favoriteEventsEvent
        }
    }

    class CalculatorDiffer : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Event, newItem: Event) = false
    }
}