package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.TaskRepository
import com.example.myapplication.model.Task
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val allTasks: StateFlow<List<Task>> = repository.tasks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    private val filterDone: MutableStateFlow<Boolean?> = MutableStateFlow(null)

    val tasks: StateFlow<List<Task>> = combine(allTasks, filterDone) { list, filter ->
        when (filter) {
            null -> list
            else -> list.filter { it.done == filter }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(task: Task) {
        viewModelScope.launch { repository.addTask(task) }
    }

    fun toggleDone(id: Int) {
        viewModelScope.launch {
            val task = repository.getTaskById(id) ?: return@launch
            repository.updateTask(task.copy(done = !task.done))
        }
    }

    fun removeTask(id: Int) {
        viewModelScope.launch {
            val task = repository.getTaskById(id) ?: return@launch
            repository.deleteTask(task)
        }
    }

    fun updateTask(updated: Task) {
        viewModelScope.launch { repository.updateTask(updated) }
    }

    fun filterByDone(done: Boolean) {
        filterDone.value = done
    }

    fun showAll() {
        filterDone.value = null
    }

    fun sortByDueDate() {

    }
}
class TaskViewModelFactory(
    private val repository: TaskRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return TaskViewModel(repository) as T
    }
}
