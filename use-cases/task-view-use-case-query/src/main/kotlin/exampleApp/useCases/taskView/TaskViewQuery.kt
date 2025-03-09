package exampleApp.useCases.taskView

import exampleApp.entities.task.TaskEntity

interface TaskViewQuery {
    suspend fun query(filter: Filter): List<TaskEntity>

    class Filter(
        val completed: TaskEntity.Data.Completed?,
    )
}
