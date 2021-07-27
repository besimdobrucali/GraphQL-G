package com.dobrucali.gorillas.di

import com.dobrucali.gorillas.data.api.PostApi
import com.dobrucali.gorillas.data.repository.PostRepository
import com.dobrucali.gorillas.data.task.PostTask
import com.dobrucali.gorillas.viewModels.MainViewModel
import com.dobrucali.gorillas.viewModels.PostDetailViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module{

    // ApolloClient
    single { PostApi() }

    // Repositories
    factory { PostRepository(get()) }

    // Use Cases
    factory { PostTask(get()) }

    // View Models
    viewModel { MainViewModel(get()) }
    viewModel { PostDetailViewModel(get()) }

}