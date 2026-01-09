package com.example.myapplication.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.*

@Composable
fun HomeScreen(tasks: List<Task>, modifier: Modifier = Modifier) {

    var currentTasks by remember { mutableStateOf(tasks) }

    var doneFilter by remember { mutableStateOf<Boolean?>(null) }

    val visibleTasks = remember(currentTasks, doneFilter) {
        when (doneFilter) {
            null -> currentTasks
            true -> filterByDone(currentTasks, true)
            false -> filterByDone(currentTasks, false)
        }
    }

    val newTask = Task(
        id = (currentTasks.maxOfOrNull { it.id } ?: 0) + 1,
        title = "New Task",
        description = "Added from button",
        priority = Priority.LOW,
        dueDate = "2026-01-20",
        done = false
    )

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {

        item {
            Text(
                text = "My Gym Plan",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = {
                    currentTasks = addTask(currentTasks, newTask)
                }) {
                    Text("Add")
                }

                Button(onClick = {
                    val firstId = currentTasks.firstOrNull()?.id ?: return@Button
                    currentTasks = toggleDone(currentTasks, firstId)
                }) {
                    Text("Toggle")
                }

                Button(onClick = {
                    currentTasks = sortByDueDate(currentTasks)
                }) {
                    Text("Sort")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { doneFilter = null }) { Text("All") }
                Button(onClick = { doneFilter = true }) { Text("Done") }
                Button(onClick = { doneFilter = false }) { Text("Not done") }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        items(visibleTasks) { task ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = task.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = task.description)
                    Text(text = "Priority: ${task.priority}")
                    Text(text = "Status: ${if (task.done) "Done" else "Not done"}")
                    Text(text = "Due: ${task.dueDate}")
                }
            }
        }
    }
}
