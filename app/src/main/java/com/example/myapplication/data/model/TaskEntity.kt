package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.model.Priority

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val description: String,

    val priority: String,

    val dueDate: String,
    val done: Boolean
)
