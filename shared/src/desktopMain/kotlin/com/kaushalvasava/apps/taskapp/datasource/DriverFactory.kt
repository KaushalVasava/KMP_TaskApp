package com.kaushalvasava.apps.taskapp.datasource


import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.kaushalvasava.apps.taskapp.TaskDatabase
import java.io.File

actual class DriverFactory {

    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(url = "jdbc:sqlite:TaskDatabase.db")
        if (!File("TaskDatabase.db").exists()) {
            TaskDatabase.Schema.create(driver)
        }
        return driver
    }
}