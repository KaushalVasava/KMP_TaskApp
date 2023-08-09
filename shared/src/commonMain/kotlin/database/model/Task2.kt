package database.model

import kotlinx.datetime.Clock

data class Task2(
    val id: Long = 0,
    val title: String = "",
    val isDone: Boolean = false,
    val color: Long,
    val date: Long = Clock.System.now().toEpochMilliseconds(),
    val isImportant:Boolean = false
)