package com.kaushalvasava.apps.taskapp.datasource

import com.squareup.sqldelight.db.SqlDriver
import taskdatabase.db.TaskDatabase

//class TaskDatabaseFactory(
//    private val driverFactory: DriverFactory
//){
//    fun createDatabase(): TaskDatabase {
//        return LocalDb(driverFactory.createDriver())
//    }
//}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): TaskDatabase {
    val driver = driverFactory.createDriver()
    return TaskDatabase(driver)
    // Do more work with the database (see below).
}
//expect class DriverFactory {
//    fun createDriver(): SqlDriver
//}
//
//fun createDatabase(driverFactory: DriverFactory): Database {
//    val driver = driverFactory.createDriver()
//    val database = Database(driver)
//    return database
//    // Do more work with the database (see below).
//}