package com.kaushalvasava.apps.taskapp.datasource

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}