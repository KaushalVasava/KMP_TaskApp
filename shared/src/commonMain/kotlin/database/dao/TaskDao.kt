package database.dao

import com.kaushalvasava.apps.taskapp.util.toTask2
import database.model.Task2
import taskdatabase.db.TaskDatabase

fun TaskDatabase.setTask(task: Task2) {
    return taskQueries.insertTask(task.id, task.title, task.isDone)
}

fun TaskDatabase.deleteTask(id: Long) {
    return taskQueries.deleteTask(id)
}

fun TaskDatabase.getTasksList(): List<Task2> {
    return this.taskQueries.getTasks().executeAsList().map {
        it.toTask2()
//        Task2(it.id, it.title, it.isDone)
    }
}

fun TaskDatabase.updateTask(title: String, isDone: Boolean, id: Long) {
    return taskQueries.updateTask(title, isDone, id)
}

fun TaskDatabase.setTaskList(list: List<Task2>) {
    taskQueries.transaction {
        list.forEach {
            taskQueries.insertTask(
                it.id, it.title, it.isDone
            )
        }
    }
}