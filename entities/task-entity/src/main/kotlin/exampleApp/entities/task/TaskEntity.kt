package exampleApp.entities.task

import java.time.LocalDateTime

class TaskEntity(
    val id: Id,
    val createdAt: LocalDateTime,
    val data: Data,
) {
    @JvmInline
    value class Id(val value: Long) {
        init {
            require(value > 0) { "Task Id must be positive." }
        }
    }

    class Data(
        val title: Title,
        val completed: Completed,
    ) {
        @JvmInline
        value class Title(val value: String) {
            init {
                require(value.isNotBlank()) { "Task Title must not be blank." }
                require(value.length <= 50) { "Task Title must not exceed 50 characters." }
            }
        }

        @JvmInline
        value class Completed(val value: Boolean)
    }
}
