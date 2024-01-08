package com.kaushalvasava.apps.taskapp.util

import com.kaushalvasava.apps.taskapp.datasource.model.Task2
import comkaushalvasavaappstaskapp.Task


fun Task2.toTask(): Task {
    return Task(id, title, isDone, color, date, isImportant)
}

fun Task.toTask2(): Task2 {
    return Task2(id, title, isDone, color, date, isImportant)
}