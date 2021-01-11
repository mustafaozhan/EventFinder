package mustafaozhan.github.com.ui.eventlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import mustafaozhan.github.com.base.BaseDBRecyclerViewAdapter
import mustafaozhan.github.com.model.Event
import mustafaozhan.github.com.ui.databinding.ItemEventListBinding

class EventListAdapter(
    private val eventListEvent: EventListEvent
) : BaseDBRecyclerViewAdapter<Event, ItemEventListBinding>(CalculatorDiffer()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CalculatorDBViewHolder(
        ItemEventListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class CalculatorDBViewHolder(itemBinding: ItemEventListBinding) :
        BaseDBViewHolder<Event, ItemEventListBinding>(itemBinding) {

        override fun onItemBind(item: Event) = with(itemBinding) {
            this.item = item
            this.event = eventListEvent
        }
    }

    class CalculatorDiffer : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Event, newItem: Event) = false
    }
}