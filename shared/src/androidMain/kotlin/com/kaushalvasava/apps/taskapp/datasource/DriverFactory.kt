package com.kaushalvasava.apps.taskapp.datasource

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import task_database.db.TaskDatabase

actual class DriverFactory(private val context: Context){
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TaskDatabase.Schema, context, "task_database.db")
    }
}