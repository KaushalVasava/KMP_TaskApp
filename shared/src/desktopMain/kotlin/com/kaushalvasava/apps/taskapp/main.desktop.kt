package com.kaushalvasava.apps.taskapp

import App
import androidx.compose.runtime.Composable
import com.kaushalvasava.apps.taskapp.datasource.DriverFactory

@Composable
fun MainView() = App(DriverFactory().createDriver(), false)