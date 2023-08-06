package com.kaushalvasava.apps.taskapp.datasource

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import mylocal2.db.LocalDb

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(LocalDb.Schema, "mylocal2.db")
    }
}