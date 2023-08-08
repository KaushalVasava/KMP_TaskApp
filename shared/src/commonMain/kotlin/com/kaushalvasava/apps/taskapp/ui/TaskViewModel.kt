import database.dao.deleteTask
import database.dao.getTasksList
import database.dao.setTask
import com.squareup.sqldelight.db.SqlDriver
import database.dao.updateTask
import database.model.Task2
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import taskdatabase.db.TaskDatabase
import kotlin.random.Random

class TaskViewModel(driver: SqlDriver) : ViewModel() {
    val tasks = MutableStateFlow<List<Task2>>(emptyList())

    private val database = TaskDatabase(driver)

    init {
        getTasks()
    }

    fun addTask(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            database.setTask(Task2(Random.nextLong(0, 100000000), title))
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
        }
    }

    fun update(title: String, isDone: Boolean, id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            database.updateTask(title, isDone, id)
            getTasks()
        }
    }
}