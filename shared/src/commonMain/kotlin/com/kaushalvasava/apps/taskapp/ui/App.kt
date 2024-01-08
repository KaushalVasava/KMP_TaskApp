import androidx.compose.foundation.background
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.sqldelight.db.SqlDriver
import com.kaushalvasava.apps.taskapp.ui.components.TaskScreen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun TaskAppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp)
        )
    ) {
        Surface(modifier = Modifier.background(MaterialTheme.colors.background)) {
            content()
        }
    }
}

@Composable
fun App(sqlDriver: SqlDriver, isTopBarVisible: Boolean) {
    TaskAppTheme {
        val birdsViewModel = getViewModel(Unit, viewModelFactory { TaskViewModel(sqlDriver) })
        TaskScreen(birdsViewModel, isTopBarVisible)
    }
}


