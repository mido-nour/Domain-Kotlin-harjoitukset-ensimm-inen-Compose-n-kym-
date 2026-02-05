package com.example.myapplication.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Priority
import com.example.myapplication.model.Task
import com.example.myapplication.viewmodel.TaskViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TaskViewModel
) {
    val tasks by viewModel.tasks.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }
    var editingTask by remember { mutableStateOf<Task?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Tasks (${tasks.size})",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(onClick = { viewModel.sortByDueDate() }) { Text("Sort") }
                OutlinedButton(onClick = { viewModel.showAll() }) { Text("All") }
                OutlinedButton(onClick = { viewModel.filterByDone(true) }) { Text("Done") }
                OutlinedButton(onClick = { viewModel.filterByDone(false) }) { Text("Not done") }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tasks) { task ->
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
                                onCheckedChange = { viewModel.toggleDone(task.id) }
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(task.title, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    task.description.ifBlank { "No description" },
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Text(
                                    "Due: ${task.dueDate} • ${task.priority}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    if (showAddDialog) {
        TaskEditDialog(
            task = Task(
                id = 0,
                title = "",
                description = "",
                dueDate = "2026-02-05",
                priority = Priority.LOW,
                done = false
            ),
            onCancel = { showAddDialog = false },
            onSave = { newTask ->
                val nextId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
                viewModel.addTask(newTask.copy(id = nextId))
                showAddDialog = false
            },
            onDelete = {
                showAddDialog = false
            }
        )
    }
    editingTask?.let { task ->
        TaskEditDialog(
            task = task,
            onCancel = { editingTask = null },
            onSave = { updatedTask ->
                viewModel.updateTask(updatedTask)
                editingTask = null
            },
            onDelete = {
                viewModel.removeTask(task.id)
                editingTask = null
            }
        )
    }
}