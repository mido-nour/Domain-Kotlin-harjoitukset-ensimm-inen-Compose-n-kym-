package com.example.myapplication.domain



data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val dueDate: String,
    val done: Boolean
)