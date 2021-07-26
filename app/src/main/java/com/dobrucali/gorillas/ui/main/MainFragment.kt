package com.dobrucali.gorillas.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dobrucali.gorillas.adapters.PostListAdapter
import com.dobrucali.gorillas.databinding.FragmentMainBinding
import com.dobrucali.gorillas.ui.base.BaseFragment
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

        binding.postRecyclerView.adapter = PostListAdapter {

        }

        return binding.root
    }

}