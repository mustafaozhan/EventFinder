package mustafaozhan.github.com.eventfinder.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

abstract class BaseDBFragment<TDataBinding : ViewDataBinding> : BaseFragment() {

    protected lateinit var binding: TDataBinding

    abstract fun bind(container: ViewGroup?): TDataBinding
    abstract fun onBinding(dataBinding: TDataBinding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = bind(container)
        binding.lifecycleOwner = viewLifecycleOwner
        onBinding(binding)
        return binding.root
    }
}