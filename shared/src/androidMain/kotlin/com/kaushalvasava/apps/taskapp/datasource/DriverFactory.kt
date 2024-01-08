package com.kaushalvasava.apps.taskapp.datasource

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.kaushalvasava.apps.taskapp.TaskDatabase
actual class DriverFactory(private val context: Context){
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            TaskDatabase.Schema, context, "TaskDatabase.db"
        )
    }
}