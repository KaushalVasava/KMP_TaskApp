package com.kaushalvasava.apps.taskapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircleCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val color = MaterialTheme.colors
    val icon =
        if (checked) Icons.Default.CheckCircle
        else Icons.Default.CheckCircle
    val tint =
        if (checked) color.primary.copy(alpha = 0.8f)
        else Color.Transparent
    val border = if (checked) Color.Transparent else color.onSurface
    IconButton(
        onClick = { onCheckedChange(!checked) },
        modifier = modifier.clip(CircleShape),
        enabled = enabled
    ) {
        Icon(
            icon,
            tint = tint,
            modifier = Modifier.clip(CircleShape).border(2.dp, border, shape = CircleShape),
            contentDescription = "checkbox"
        )
    }
}
