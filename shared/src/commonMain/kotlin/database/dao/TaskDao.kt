package database.dao

import com.kaushalvasava.apps.taskapp.util.toTask2
import database.model.Task2
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import task_database.db.TaskDatabase

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
    return this.taskQueries.getTasks().executeAsList().map {
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