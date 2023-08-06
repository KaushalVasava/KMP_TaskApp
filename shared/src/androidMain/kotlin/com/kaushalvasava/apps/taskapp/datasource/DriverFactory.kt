package com.kaushalvasava.apps.taskapp.datasource

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import mylocal3.db.LocalDb

actual class DriverFactory(private val context: Context){
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(LocalDb.Schema, context, "mylocal3.db")
    }
}