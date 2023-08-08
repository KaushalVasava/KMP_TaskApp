package com.kaushalvasava.apps.taskapp.datasource

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import taskdatabase.db.TaskDatabase
import java.io.File

actual class DriverFactory {
    /*   actual fun createDriver(): SqlDriver {
           val databasePath = File(System.getProperty("java.io.tmpdir"), "mylocal2.db")
   //        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
   //        return TaskDatabase.Schema.create(driver)
           return JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.absolutePath}")
   //        return driver
       }

     */
    actual fun createDriver(): SqlDriver {

//        val databasePath =
//            File(System.getProperty("java.io.tmpdir"), "mylocal2.db")
        val driver = JdbcSqliteDriver(url = "jdbc:sqlite:taskdatabase.db")
//                " ${databasePath.path}")
        if (!File("taskdatabase.db").exists()) {
            TaskDatabase.Schema.create(driver)
        }
        return driver
    }
}