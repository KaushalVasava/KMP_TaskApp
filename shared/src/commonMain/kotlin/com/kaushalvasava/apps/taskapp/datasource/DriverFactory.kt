package com.kaushalvasava.apps.taskapp.datasource

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}