package com.kaushalvasava.apps.taskapp.datasource.dao

import com.kaushalvasava.apps.taskapp.TaskDatabase
import com.kaushalvasava.apps.taskapp.datasource.model.Task2
import com.kaushalvasava.apps.taskapp.util.toTask2

fun TaskDatabase.setTask(task: Task2) {
    return taskQueries.insertTask(
        task.id,
        task.title,
        task.isDone,
        task.color,
        task.date,
        task.isImportant
    )
}

fun TaskDatabase.deleteTask(id: Long) {
    return taskQueries.deleteTask(id)
}

fun TaskDatabase.getTasksList(): List<Task2> {
    return this.taskQueries.getTasks().executeAsList()
        .sortedByDescending { it.date }
        .sortedByDescending { it.isImportant }.map {
            it.toTask2()
        }
}

fun TaskDatabase.updateTask(task: Task2) {
    return taskQueries.updateTask(
        task.title,
        task.isDone,
        task.color,
        task.date,
        task.isImportant,
        task.id
    )
}