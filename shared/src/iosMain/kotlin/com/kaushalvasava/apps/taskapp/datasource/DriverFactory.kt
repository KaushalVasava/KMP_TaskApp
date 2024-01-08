package com.kaushalvasava.apps.taskapp.datasource

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.kaushalvasava.apps.taskapp.TaskDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(TaskDatabase.Schema, "TaskDatabase.db")
    }
}