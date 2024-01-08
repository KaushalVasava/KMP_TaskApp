package com.kaushalvasava.apps.taskapp.ui.components

import TaskViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel, isTopBarVisible: Boolean) {
    val tasks by viewModel.tasks.collectAsState()
    val colors = listOf(
        Color(0xFFF49E9E), Color(0xFF91D2F1),
        Color(0xFF97F19B), Color(0xFFF1E197),
        Color(0xFFA785EC)
    )
    var isCompleted by rememberSaveable {
        mutableStateOf(false)
    }
    var selectedColor by remember {
        mutableStateOf(colors.first())
    }
    var text: String by rememberSaveable {
        mutableStateOf("")
    }
    var id: Long? by rememberSaveable {
        mutableStateOf(null)
    }
    if (tasks.isEmpty()) {
        AddNote(
            selectedColor = selectedColor,
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
            isCompleted = isCompleted,
            title = text,
            onTaskAdd = {
                viewModel.addTask(it)
            },
            onTextChange = {
                text = it
            },
            onColorSelection = {
                selectedColor = it
            }
        ) {
            isCompleted = it
        }
    } else {
        Scaffold(
            topBar = {
                if (isTopBarVisible) {
                    TopAppBar {
                        Text("Task", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }
            },
            bottomBar = {
                AddNote(
                    title = text,
                    selectedColor = selectedColor,
                    modifier = Modifier,
                    isCompleted = isCompleted,
                    onTaskAdd = {
                        if (id != null) {
                            viewModel.update(it.copy(id = id!!))
                            id = null
                        } else
                            viewModel.addTask(it)
                    },
                    onTextChange = {
                        text = it
                    },
                    onColorSelection = {
                        selectedColor = it
                    }
                ) {
                    isCompleted = it
                }
            }
        ) { paddingValues ->
            Box(Modifier.fillMaxSize().padding(paddingValues)) {
                if (isTopBarVisible) {
                    LazyColumn(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {

                        if(isCompleted) {
                            item {
                                Text("Completed Tasks")
                            }
                        }
                        items(tasks.filter { isCompleted == it.isDone }, key = {
                            it.id + it.hashCode() + it.date

                        }) {
                            Row(Modifier.animateItemPlacement()) {
                                TaskItem(viewModel, it) {
                                    text = it.title
                                    id = it.id
                                    selectedColor = Color(it.color)
                                }
                            }
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(420.dp),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        if (isCompleted) {
                            item(span = {
                                GridItemSpan(1)
                            }) {
                                Text("Completed Tasks")
                            }
                        }
                        items(
                            tasks.filter { isCompleted == it.isDone },
                            key = {
                                it.id + it.hashCode() + it.date
                            }
                        ) {
                            Row(Modifier.animateItemPlacement()) {
                                TaskItem(viewModel, it) {
                                    text = it.title
                                    id = it.id
                                    selectedColor = Color(it.color)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}