import database.dao.deleteTask
import database.dao.getTasksList
import database.dao.setTask
import com.squareup.sqldelight.db.SqlDriver
import database.model.Task2
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mylocal3.db.LocalDb
import kotlin.random.Random

class BirdsViewModel(driver: SqlDriver) : ViewModel() {
    val tasks = MutableStateFlow<List<Task2>>(emptyList())

    private val database = LocalDb(driver)

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
}