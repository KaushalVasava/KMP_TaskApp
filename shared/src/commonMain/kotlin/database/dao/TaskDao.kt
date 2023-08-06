package database.dao

import database.model.Task2
import mylocal3.db.LocalDb

fun LocalDb.setTask(task: Task2) {
    return taskQueries.insertTask(task.id, task.title, task.isDone)
}

fun LocalDb.deleteTask(id: Long) {
    return taskQueries.deleteTask(id)
}

fun LocalDb.getTasksList(): List<Task2> {
    return this.taskQueries.getTasks().executeAsList().map {
        Task2(it.id, it.title, it.isDone)
    }
}

fun LocalDb.setTaskList(list: List<Task2>) {
    taskQueries.transaction {
        list.forEach {
            taskQueries.insertTask(
                it.id,it.title, it.isDone
            )
        }
    }
}