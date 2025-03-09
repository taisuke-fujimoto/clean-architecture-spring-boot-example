package exampleApp.gateways.postgresql.clients

import exampleApp.entities.task.TaskEntity
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Suppress("PropertyName")
@Table("task")
internal data class TaskRecord(
    @Id var id: Long,
    val title: String,
    val completed: Boolean,
    val created_at: LocalDateTime,
) {
    companion object {
        fun new(data: TaskEntity.Data): TaskRecord =
            TaskRecord(
                id = 0,
                title = data.title.value,
                completed = data.completed.value,
                created_at = LocalDateTime.now(),
            )
    }

    fun toEntity(): TaskEntity =
        TaskEntity(
            TaskEntity.Id(id),
            created_at,
            TaskEntity.Data(
                TaskEntity.Data.Title(title),
                TaskEntity.Data.Completed(completed),
            ),
        )
}
