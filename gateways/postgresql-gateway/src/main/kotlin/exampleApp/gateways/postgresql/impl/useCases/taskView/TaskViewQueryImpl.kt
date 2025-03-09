package exampleApp.gateways.postgresql.impl.useCases.taskView

import exampleApp.entities.task.TaskEntity
import exampleApp.gateways.postgresql.clients.TaskTableClient
import exampleApp.useCases.taskView.TaskViewQuery
import org.springframework.stereotype.Component

@Component
internal class TaskViewQueryImpl(
    private val taskTableClient: TaskTableClient,
) : TaskViewQuery {
    override suspend fun query(filter: TaskViewQuery.Filter): List<TaskEntity> =
        taskTableClient.selectListOrderByCreatedAtDesc(completed = filter.completed?.value)
            .map { it.toEntity() }
}
