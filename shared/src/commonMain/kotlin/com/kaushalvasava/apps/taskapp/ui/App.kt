import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import kotlin.random.Random

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
fun App(sqlDriver: SqlDriver) {
    TaskAppTheme {
        val birdsViewModel = getViewModel(Unit, viewModelFactory { TaskViewModel(sqlDriver) })
        TaskList(birdsViewModel)
    }
}

@Composable
fun TaskList(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    val colors = listOf(
        Color(0xFFF49E9E), Color(0xFF91D2F1), Color(0xFF97F19B),
        Color(0xFFF1E197), Color(0xFFA785EC)
    )
    var txt by remember {
        mutableStateOf("")
    }
    var selectedColor by remember {
        mutableStateOf(colors.first())
    }
    val isImp by remember {
        mutableStateOf(false)
    }
    if (tasks.isEmpty()) {
        AddNote(selectedColor, viewModel, Modifier.padding(horizontal = 8.dp), onColorSelection = {
            selectedColor = it
        })
//        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
//            TextField(
//                value = txt,
//                onValueChange = { txt = it },
//                trailingIcon = {
//                    Icon(
//                        Icons.Default.Done,
//                        contentDescription = "Add",
//                        modifier = Modifier.clickable {
//                            viewModel.addTask(
//                                Task2(
//                                    title = txt,
//                                    color = Color.Green.hashCode().toLong(),
//                                    isImportant = isImp
//                                )
//                            )
//                            txt = ""
//                        })
//                },
//                modifier = Modifier.fillMaxWidth(1f).padding(16.dp).align(Alignment.Center),
//                placeholder = {
//                    Text("Add task", color = Color.Gray)
//                }
//            )
//        }
    } else {
        Box(Modifier.padding(horizontal = 8.dp)) {
            AnimatedVisibility(tasks.isNotEmpty()) {
                LazyColumn(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    items(tasks.filter { !it.isDone }) {
                        TaskItem(viewModel, it)
                    }
                    item {
                        Text("Completed")
                    }
                    items(tasks.filter { it.isDone }) {
                        TaskItem(viewModel, it)
                    }
                }
            }
            AddNote(selectedColor, viewModel, modifier = Modifier, onColorSelection = {
                selectedColor = it
            })
        }
    }
}

@Composable
fun AddNote(
    selectedColor: Color,
    viewModel: TaskViewModel,
    modifier: Modifier,
    onColorSelection: (Color) -> Unit,
) {
    val colors = listOf(
        Color(0xFFF49E9E), Color(0xFF91D2F1), Color(0xFF97F19B),
        Color(0xFFF1E197), Color(0xFFA785EC)
    )
    var txt by remember {
        mutableStateOf("")
    }
    val isImp by remember {
        mutableStateOf(false)
    }
    Box(modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).clip(
                RoundedCornerShape(16.dp)
            ).background(Color.White)
        ) {
            TextField(
                value = txt,
                onValueChange = { txt = it },
                trailingIcon = {
                    Icon(
                        Icons.Default.Done,
                        contentDescription = "Add",
                        modifier = Modifier.clickable {
                            viewModel.addTask(
                                Task2(
                                    id = Random.nextLong(0, 10000000L),
                                    title = txt,
                                    color = selectedColor.hashCode().toLong(),
                                    isImportant = isImp
                                )
                            )
                            txt = ""
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth(1f)
                    .padding(horizontal = 4.dp, vertical = 16.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    ).background(MaterialTheme.colors.background),
                placeholder = {
                    Text("Add task", color = Color.Gray)
                }
            )
            LazyRow(Modifier.fillMaxWidth().padding(horizontal = 4.dp)) {
                items(colors) {
                    Card(modifier = Modifier.padding(4.dp).clip(CircleShape).clickable {
                        onColorSelection(it)
//                        selectedColor = it
                    }) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(40.dp).background(it)
                        ) {
                            if (it == selectedColor) {
                                Icon(
                                    Icons.Default.Done,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(viewModel: TaskViewModel, task: Task2) {
    var isCompleted by remember {
        mutableStateOf(task.isDone)
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
                .background(Brush.horizontalGradient(listOf(Color(task.color), Color.Transparent)))
                .padding(horizontal = 4.dp).clip(
                    RoundedCornerShape(8.dp)
                )
        ) {
            Checkbox(
                checked = task.isImportant, onCheckedChange = {
                    viewModel.update(task.copy(isImportant = it))
                },
                colors = CheckboxDefaults.colors(Color.Black)
            )
            Text(
                task.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                isCompleted,
                onCheckedChange = {
                    viewModel.update(task.copy(isDone = it))
                    isCompleted = it
                },
                colors = CheckboxDefaults.colors(Color.Green)
            )
            IconButton(
                onClick = {
                    viewModel.deleteTask(task.id)
                }
            ) {
                Icon(Icons.Default.Delete, contentDescription = "delete")
            }
        }
    }
}