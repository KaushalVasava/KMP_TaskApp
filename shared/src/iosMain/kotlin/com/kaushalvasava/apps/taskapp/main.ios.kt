package com.kaushalvasava.apps.taskapp

import App
import androidx.compose.ui.window.ComposeUIViewController
import com.kaushalvasava.apps.taskapp.datasource.DriverFactory

fun MainViewController() = ComposeUIViewController { App(DriverFactory().createDriver()) }