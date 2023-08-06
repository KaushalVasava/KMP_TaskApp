package com.kaushalvasava.apps.taskapp

import App
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.kaushalvasava.apps.taskapp.datasource.DriverFactory

@Composable
fun MainView() = App(DriverFactory().createDriver())

//@Preview
//@Composable
//fun AppPreview() {
//    App(appDataSource)
//}
//
//class DesktopPlatform : Platform {
//    override val name: String = "Desktop"
//}
//
//actual fun getPlatform(): Platform = DesktopPlatform()