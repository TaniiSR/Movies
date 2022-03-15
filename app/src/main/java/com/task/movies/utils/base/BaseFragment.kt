package com.task.movies.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.task.movies.utils.base.interfaces.IBaseViewModel
import com.task.movies.utils.base.interfaces.OnBackPressedListener
import com.task.movies.utils.extensions.observe

abstract class BaseFragment<VB : ViewBinding, VM : IBaseViewModel>(@LayoutRes val contentLayoutId: Int) :
    Fragment(contentLayoutId),
    OnBackPressedListener, View.OnClickListener {

    private lateinit var onBackPressedCallback: OnBackPressedCallback
    lateinit var mViewBinding: VB
    abstract val viewModel: VM
    abstract fun onClick(id: Int)
    abstract fun getViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getViewBinding()
        return mViewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        observe(viewModel.clickEvent, ::handleClickEvent)
    }

    override fun onClick(view: View) {
        viewModel.onClick(view)
    }

    private fun handleClickEvent(clickEventId: Int) {
        onClick(clickEventId)
    }

    protected fun showToast(msg: String) {
        if (activity is BaseActivity<*, *>)
            (activity as BaseActivity<*, *>).showToast(msg)
    }

    fun setBackButtonDispatcher() {
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onBackPressed(): Boolean = true

    override fun onDestroyView() {
        viewModel.clickEvent.removeObservers(this)
        super.onDestroyView()
    }
}
