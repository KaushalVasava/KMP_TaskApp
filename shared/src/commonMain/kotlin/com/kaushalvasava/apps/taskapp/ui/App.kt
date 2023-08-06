import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.squareup.sqldelight.db.SqlDriver
import database.model.Task2
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun TaskAppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp)
        )
    ) {
        content()
    }
}

@Composable
fun App(sqlDriver: SqlDriver) {
    TaskAppTheme {
        val birdsViewModel = getViewModel(Unit, viewModelFactory { BirdsViewModel(sqlDriver) })
//        BirdsPage(birdsViewModel)
        TaskList(birdsViewModel)
    }
}

@Composable
fun TaskList(viewModel: BirdsViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    var txt by remember {
        mutableStateOf("")
    }
    if (tasks.isEmpty()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            TextField(
                value = txt,
                onValueChange = { txt = it },
                trailingIcon = {
                    Icon(
                        Icons.Default.Done,
                        contentDescription = "Add",
                        modifier = Modifier.clickable {
                            viewModel.addTask(txt)
                            txt = ""
                        })
                },
                modifier = Modifier.fillMaxWidth(1f).padding(16.dp).align(Alignment.Center),
                placeholder = {
                    Text("Add task", color = Color.Gray)
                }
            )
        }
    } else {
        Box(Modifier.padding(horizontal = 8.dp)) {
            AnimatedVisibility(tasks.isNotEmpty()) {
                LazyColumn(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    items(tasks) {
                        TaskItem(viewModel, it)
                    }
                }
            }
            TextField(
                value = txt,
                onValueChange = { txt = it },
                trailingIcon = {
                    Icon(
                        Icons.Default.Done,
                        contentDescription = "Add",
                        modifier = Modifier.clickable {
                            viewModel.addTask(txt)
                            txt = ""
                        })
                },
                modifier = Modifier.fillMaxWidth(1f)
                    .padding(horizontal = 4.dp, vertical = 16.dp)
                    .align(Alignment.BottomCenter).clip(
                        RoundedCornerShape(8.dp)
                    ),
                placeholder = {
                    Text("Add task", color = Color.Gray)
                }
            )
        }
    }
}

@Composable
fun TaskItem(viewModel: BirdsViewModel, task: Task2) {
    var isCompleted by remember {
        mutableStateOf(false)
    }
    Card(
        Modifier.padding(4.dp).clip(
            RoundedCornerShape(8.dp)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .background(Brush.horizontalGradient(listOf(Color.Green, Color.Transparent)))
                .padding(horizontal = 4.dp).clip(
                    RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                task.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(isCompleted, onCheckedChange = {
                if (it) {
                    viewModel.deleteTask(task.id)
                }
                isCompleted = it
            }, colors = CheckboxDefaults.colors(Color.Green))
        }
    }
}