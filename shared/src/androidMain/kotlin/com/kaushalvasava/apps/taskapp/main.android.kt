package com.kaushalvasava.apps.taskapp

import App
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.kaushalvasava.apps.taskapp.datasource.DriverFactory

@Composable fun MainView() = App(DriverFactory(LocalContext.current.applicationContext).createDriver())
