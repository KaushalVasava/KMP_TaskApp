import com.squareup.sqldelight.db.SqlDriver
import database.dao.deleteTask
import database.dao.getTasksList
import database.dao.setTask
import database.dao.updateTask
import database.model.Task2
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import task_database.db.TaskDatabase

class TaskViewModel(driver: SqlDriver) : ViewModel() {
    val tasks = MutableStateFlow<List<Task2>>(emptyList())

    private val database = TaskDatabase(driver)

    init {
        getTasks()
    }

    fun addTask(task: Task2) {
        viewModelScope.launch(Dispatchers.IO) {
            database.setTask(task)
            tasks.value = database.getTasksList()
        }
    }

    fun deleteTask(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            database.deleteTask(id)
            tasks.value = database.getTasksList()
        }
    }

    private fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            tasks.value = database.getTasksList()
                .sortedByDescending { it.date }
                .sortedByDescending { it.isImportant }
        }
    }

    fun update(task2: Task2) {
        viewModelScope.launch(Dispatchers.IO) {
            database.updateTask(task2)
            getTasks()
        }
    }
}