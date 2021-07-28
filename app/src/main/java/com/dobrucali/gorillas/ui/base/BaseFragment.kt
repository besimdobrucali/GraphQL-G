package com.dobrucali.gorillas.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.dobrucali.gorillas.ui.main.MainActivity
import com.dobrucali.gorillas.utils.hideKeyboard
import com.dobrucali.gorillas.utils.toast
import com.dobrucali.gorillas.viewModels.BaseViewModel

abstract class BaseFragment <VM : BaseViewModel> : Fragment() {
    private var initialized: Boolean = false

    protected abstract val viewModel: VM

    /**
     * Call when creating view on super class, however this function returns null
     *
     * Overriding class should create their own implementation and return that
     */
    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.bindCommands()
        this.initialized = true

        observeErrorMessage()

        viewModel.isStatusLoading.observe(viewLifecycleOwner, { isStatusNotLoading ->
            isStatusNotLoading?.let {
                if (requireActivity() is MainActivity) {
                    (requireActivity() as MainActivity).handleLoading(isStatusNotLoading)
                }
            }
        })

        return null
    }

    @CallSuper
    protected open fun bindCommands() {

    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun navigate(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun observeErrorMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner, { message ->
            if (activity != null){
                message?.let {
                    requireActivity().toast(message)
                }
            }
        })
    }

    protected fun isFragmentVisible(fragmentTag: String) =
        childFragmentManager.fragments.map { it.tag }.contains(fragmentTag)


    fun hideKeyboard() {
        (activity as AppCompatActivity).hideKeyboard()
    }

}