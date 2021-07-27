package com.dobrucali.gorillas.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dobrucali.gorillas.R
import com.dobrucali.gorillas.adapters.PostListAdapter
import com.dobrucali.gorillas.databinding.FragmentMainBinding
import com.dobrucali.gorillas.ui.base.BaseFragment
import com.dobrucali.gorillas.utils.Constants
import com.dobrucali.gorillas.viewModels.MainViewModel
import org.koin.android.ext.android.inject

class MainFragment : BaseFragment<MainViewModel>() {

    override val viewModel: MainViewModel by inject()
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.postRecyclerView.adapter = PostListAdapter { post ->
            navigatePostDetail(post.id)
        }

        return binding.root
    }

    private fun navigatePostDetail(postId: String?) {
        val arguments = Bundle()
        arguments.putString(Constants.POST_ID_KEY, postId)
        findNavController().navigate(
            R.id.action_main_fragment_to_detail_fragment,
            arguments,
            null,
            null
        )
    }
}