package com.dobrucali.gorillas.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dobrucali.gorillas.databinding.FragmentPostDetailBinding
import com.dobrucali.gorillas.ui.base.BaseFragment
import com.dobrucali.gorillas.viewModels.PostDetailViewModel
import org.koin.android.ext.android.inject

class PostDetailFragment : BaseFragment<PostDetailViewModel>() {

    override val viewModel: PostDetailViewModel by inject()
    lateinit var binding: FragmentPostDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentPostDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        arguments?.let {
            viewModel.bindArguments(it)
        }

        return binding.root
    }


}