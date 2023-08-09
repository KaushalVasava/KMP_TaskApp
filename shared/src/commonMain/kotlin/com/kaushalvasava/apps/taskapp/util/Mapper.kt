package com.kaushalvasava.apps.taskapp.util

import database.Task
import database.model.Task2

fun Task2.toTask(): Task {
    return Task(id, title, isDone, color, date, isImportant)
}

fun Task.toTask2(): Task2 {
    return Task2(id, title, isDone, color, date, isImportant)
}