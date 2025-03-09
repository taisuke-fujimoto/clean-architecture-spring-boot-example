package exampleApp.gateways.postgresql.impl.entities.task

import exampleApp.entities.task.TaskEntity
import exampleApp.entities.task.TaskEntityWriter
import exampleApp.gateways.postgresql.clients.TaskRecord
import exampleApp.gateways.postgresql.clients.TaskTableClient
import org.springframework.stereotype.Component

@Component
internal class TaskEntityWriterImpl(
    private val taskTableClient: TaskTableClient,
) : TaskEntityWriter {
    override suspend fun saveNew(data: TaskEntity.Data): TaskEntity {
        val record = TaskRecord.new(data)
        taskTableClient.insert(record)
        return record.toEntity()
    }

    override suspend fun updateCompleted(id: TaskEntity.Id, completed: TaskEntity.Data.Completed): Long =
        taskTableClient.updateCompleted(id.value, completed.value)
}
