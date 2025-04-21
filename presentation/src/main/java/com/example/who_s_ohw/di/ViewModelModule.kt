package com.example.who_s_ohw.di

import com.example.who_s_ohw.ui.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel()
    }

}