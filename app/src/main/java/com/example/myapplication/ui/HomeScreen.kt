package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.domain.Priority
import com.example.myapplication.domain.Task
import com.example.myapplication.viewModel.TaskViewModel

@Composable
fun HomeScreen(vm: TaskViewModel = viewModel()) {

    var newTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Tasks (${vm.tasks.size})",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newTitle,
                onValueChange = { newTitle = it },
                modifier = Modifier.weight(1f),
                label = { Text("New task") },
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                val title = newTitle.trim()
                if (title.isNotEmpty()) {
                    vm.addTask(
                        Task(
                            id = (vm.tasks.maxOfOrNull { it.id } ?: 0) + 1,
                            title = title,
                            description = "",
                            priority = Priority.LOW,
                            dueDate = "2026-01-20",
                            done = false
                        )
                    )
                    newTitle = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(onClick = { vm.sortByDueDate() }) {
                Text("Sort")
            }
            OutlinedButton(onClick = { vm.showAll() }) {
                Text("All")
            }
            OutlinedButton(onClick = { vm.filterByDone(true) }) {
                Text("Done")
            }
            OutlinedButton(onClick = { vm.filterByDone(false) }) {
                Text("Not done")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(vm.tasks) { task ->
                Card {
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
                            Text(text = task.title)
                            Text(
                                text = task.dueDate,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        TextButton(onClick = { vm.removeTask(task.id) }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}

