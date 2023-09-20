package com.kaushalvasava.apps.taskapp.datasource

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import task_database.db.TaskDatabase
import java.io.File

actual class DriverFactory {

    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(url = "jdbc:sqlite:task_database.db")
        if (!File("task_database.db").exists()) {
            TaskDatabase.Schema.create(driver)
        }
        return driver
    }
}