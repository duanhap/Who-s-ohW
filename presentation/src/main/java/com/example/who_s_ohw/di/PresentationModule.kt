package com.example.who_s_ohw.di

import org.koin.dsl.module

val presentationModule = module {
     includes(viewModelModule)

}