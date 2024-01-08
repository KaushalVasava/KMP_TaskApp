import app.cash.sqldelight.db.SqlDriver
import com.kaushalvasava.apps.taskapp.TaskDatabase
import com.kaushalvasava.apps.taskapp.datasource.dao.deleteTask
import com.kaushalvasava.apps.taskapp.datasource.dao.getTasksList
import com.kaushalvasava.apps.taskapp.datasource.dao.setTask
import com.kaushalvasava.apps.taskapp.datasource.dao.updateTask
import com.kaushalvasava.apps.taskapp.datasource.model.Task2
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class TaskViewModel(driver: SqlDriver) : ViewModel() {
    val tasks = MutableStateFlow<List<Task2>>(emptyList())

    private val database = TaskDatabase(driver)

    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            tasks.value = database.getTasksList()
        }
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

    fun update(task2: Task2) {
        viewModelScope.launch(Dispatchers.IO) {
            database.updateTask(task2.copy(date = Clock.System.now().toEpochMilliseconds(),))
            getTasks()
        }
    }
}