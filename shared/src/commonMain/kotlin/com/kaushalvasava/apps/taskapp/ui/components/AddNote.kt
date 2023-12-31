package com.kaushalvasava.apps.taskapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.kaushalvasava.apps.taskapp.datasource.model.Task2
import kotlin.random.Random

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddNote(
    title: String,
    selectedColor: Color,
    modifier: Modifier,
    isCompleted: Boolean,
    onTaskAdd: (Task2) -> Unit,
    onTextChange: (String) -> Unit,
    onColorSelection: (Color) -> Unit,
    onCheckedChange: (Boolean) -> Unit,
) {
    val colors = listOf(
        Color(0xFFF49E9E), Color(0xFF91D2F1), Color(0xFF97F19B),
        Color(0xFFF1E197), Color(0xFFA785EC)
    )

    val isImp by rememberSaveable {
        mutableStateOf(false)
    }
    Box(modifier) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .background(Color.White),
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = title,
                onValueChange = {
                    onTextChange(it)
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Done,
                        contentDescription = "Add",
                        modifier = Modifier.clickable {
                            if (title.isNotEmpty()) {
                                val task = Task2(
                                    id = Random.nextLong(0, 10000000L),
                                    title = title,
                                    color = selectedColor.hashCode().toLong(),
                                    isImportant = isImp
                                )
                                onTaskAdd(task)
                                onTextChange("")
                            }
                        }
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    val task = Task2(
                        id = Random.nextLong(0, 10000000L),
                        title = title,
                        color = selectedColor.hashCode().toLong(),
                        isImportant = isImp
                    )
                    if (title.isNotEmpty()) {
                        onTaskAdd(task)
                        onTextChange("")
                        this.defaultKeyboardAction(imeAction = ImeAction.Done)
                    }
                }),
                modifier = Modifier
                    .widthIn(400.dp)
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    ).background(MaterialTheme.colors.background),
                placeholder = {
                    Text("Add task", color = Color.Gray)
                }
            )
            LazyRow(
                Modifier.padding(horizontal = 4.dp)
            ) {
                items(colors) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(it).clickable {
                                onColorSelection(it)
                            }
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
                item {
                    Spacer(Modifier.width(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .semantics(mergeDescendants = true) { }
                            .toggleable(isCompleted, role = Role.Switch) {
                                onCheckedChange(it)
                            },
                    ) {
                        Text("Completed")
                        Spacer(Modifier.width(8.dp))
                        Switch(isCompleted, onCheckedChange = null)
                    }
                }
            }
        }
    }
}
