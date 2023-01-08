package com.sbnl.headachetracker

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                module {
                    viewModel { MainViewModel() }
                }
            )
        }
    }
}