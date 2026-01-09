package com.example.myapplication.domain

enum class Priority {
    HIGH, MEDIUM, LOW
}

val mockTasks: List<Task> = listOf(
    Task(
        id = 1,
        title = "Sali – rinta",
        description = "Rintatreeni (penkkipunnerrus, flyes)",
        priority = Priority.HIGH,
        dueDate = "2026-01-08",
        done = false
    ),
    Task(
        id = 2,
        title = "Sali – selkä",
        description = "Selkätreeni (soudut, leuanvedot)",
        priority = Priority.HIGH,
        dueDate = "2026-01-09",
        done = false
    ),
    Task(
        id = 3,
        title = "Sali – jalat",
        description = "Jalkatreeni (kyykyt, askelkyykyt)",
        priority = Priority.HIGH,
        dueDate = "2026-01-10",
        done = true
    ),
    Task(
        id = 4,
        title = "Sali – olkapäät",
        description = "Olkapäätreeni (pystypunnerrus, vipunostot)",
        priority = Priority.MEDIUM,
        dueDate = "2026-01-11",
        done = false
    ),
    Task(
        id = 5,
        title = "Sali – kädet",
        description = "Hauis ja ojentaja",
        priority = Priority.MEDIUM,
        dueDate = "2026-01-12",
        done = false
    ),
    Task(
        id = 6,
        title = "Sali – keskivartalo",
        description = "Vatsat ja core",
        priority = Priority.LOW,
        dueDate = "2026-01-13",
        done = false
    ),
    Task(
        id = 7,
        title = "Lepopäivä",
        description = "Palautuminen ja venyttely",
        priority = Priority.LOW,
        dueDate = "2026-01-14",
        done = true
    )
)
