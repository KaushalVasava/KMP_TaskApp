package com.kaushalvasava.apps.taskapp.ui.components

import TaskViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import database.model.Task2
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(
    viewModel: TaskViewModel,
    task: Task2,
    onItemClick: () -> Unit,
) {
    var isCompleted by remember {
        mutableStateOf(task.isDone)
    }
    val isImp by remember {
        mutableStateOf(task.isImportant)
    }

    var show by rememberSaveable { mutableStateOf(true) }
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                show = false
                true
            } else
                false
        })
    AnimatedVisibility(
        show,
        modifier = Modifier.padding(vertical = 4.dp),
        exit = fadeOut(spring(stiffness = Spring.StiffnessLow))
    ) {
        SwipeToDismiss(
            state = dismissState,
            background = {
                DismissBackground(dismissState)
            },
            dismissContent = {
                Card(
                    modifier = Modifier.clip(
                        RoundedCornerShape(8.dp)
                    ),
                    onClick = {
                        onItemClick()
                    }
                ) {
                    Box {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(
                                            Color(task.color),
                                            Color.Transparent
                                        )
                                    )
                                )
                        ) {
                            CircleCheckbox(
                                isCompleted,
                                onCheckedChange = {
                                    viewModel.update(task.copy(isDone = it))
                                    isCompleted = it
                                }
                            )
                            Text(
                                task.title,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            IconButton(
                                onClick = {
                                    viewModel.deleteTask(task.id)
                                }
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "delete")
                            }
                        }
                        if(isImp) {
                            Icon(
                                imageVector = Icons.Outlined.Favorite,
                                "null",
                                modifier = Modifier.padding(top = 2.dp, start = 2.dp).size(15.dp).align(
                                    Alignment.TopStart
                                )
                            )
                        }
                    }
                }
            }
        )
    }
    LaunchedEffect(show) {
        if (!show) {
            delay(800)
            when (dismissState.dismissDirection) {
                DismissDirection.EndToStart -> {
                    viewModel.deleteTask(task.id)
                }

                DismissDirection.StartToEnd -> {
                    viewModel.update(task.copy(isImportant = !isImp))
                }

                else -> {
                    dismissState.reset()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val color = when (dismissState.dismissDirection) {
        DismissDirection.StartToEnd -> Color(0xFF039BE5)
        DismissDirection.EndToStart -> Color(0xFFFF1744)
        null -> Color.Transparent
    }
    val direction = dismissState.dismissDirection

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (direction == DismissDirection.StartToEnd)
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite"
            )
        Spacer(modifier = Modifier)
        if (direction == DismissDirection.EndToStart)
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete"
            )
    }
}