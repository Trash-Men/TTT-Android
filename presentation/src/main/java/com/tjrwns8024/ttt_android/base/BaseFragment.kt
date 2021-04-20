package com.tjrwns8024.ttt_android.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.library.baseAdapters.BR
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    abstract val viewModel: BaseViewModel

    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.lifecycleOwner = viewLifecycleOwner
        _binding?.setVariable(BR.vm, viewModel)

        observeEvents()
    }

    abstract fun observeEvents()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}