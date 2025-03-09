package exampleApp.gateways.postgresql.impl.entities.task

import exampleApp.entities.task.TaskEntity
import exampleApp.entities.task.TaskEntityReader
import exampleApp.gateways.postgresql.clients.TaskTableClient
import org.springframework.stereotype.Component

@Component
internal class TaskEntityReaderImpl(
    private val taskTableClient: TaskTableClient,
) : TaskEntityReader {
    override suspend fun getOrNull(id: TaskEntity.Id): TaskEntity? = taskTableClient
        .selectOneById(id.value)
        ?.toEntity()
}
