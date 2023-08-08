package com.kaushalvasava.apps.taskapp.datasource

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import taskdatabase.db.TaskDatabase

actual class DriverFactory(private val context: Context){
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TaskDatabase.Schema, context, "taskdatabase.db")
    }
}