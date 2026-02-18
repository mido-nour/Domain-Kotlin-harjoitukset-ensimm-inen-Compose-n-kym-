package com.example.myapplication.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Task
import com.example.myapplication.viewmodel.TaskViewModel

@Composable
fun CalendarScreen(vm: TaskViewModel) {
    val tasks by vm.tasks.collectAsState()

    var editingTask by remember { mutableStateOf<Task?>(null) }

    val groupedTasks = remember(tasks) {
        tasks.sortedBy { it.dueDate }.groupBy { it.dueDate }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        Text(
            text = "Calendar View",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            groupedTasks.forEach { (date, tasksInDate) ->
                item {
                    Text(
                        text = "Due: $date",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                items(tasksInDate) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { editingTask = task }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = task.done,
                                onCheckedChange = { vm.toggleDone(task.id) }
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = task.title,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = task.description.ifBlank { "No description" },
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Text(
                                    text = "Due: ${task.dueDate} • ${task.priority}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    editingTask?.let { task ->
        TaskEditDialog(
            task = task,
            onCancel = { editingTask = null },
            onSave = { updatedTask ->
                vm.updateTask(updatedTask)
                editingTask = null
            },
            onDelete = {
                vm.removeTask(task.id)
                editingTask = null
            }
        )
    }
}