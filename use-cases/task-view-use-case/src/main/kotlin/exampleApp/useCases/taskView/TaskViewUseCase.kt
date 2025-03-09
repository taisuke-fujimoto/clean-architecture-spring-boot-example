package exampleApp.useCases.taskView

import exampleApp.entities.task.TaskEntity

interface TaskViewUseCase {
    suspend fun handle(input: Input): Output

    class Input(val filter: Filter) {
        class Filter(
            val completed: TaskEntity.Data.Completed?,
        )
    }

    class Output(val data: List<TaskEntity>)
}
