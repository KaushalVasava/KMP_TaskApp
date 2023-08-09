package com.kaushalvasava.apps.taskapp.datasource

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import task_database.db.TaskDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(TaskDatabase.Schema, "task_database.db")
    }
}