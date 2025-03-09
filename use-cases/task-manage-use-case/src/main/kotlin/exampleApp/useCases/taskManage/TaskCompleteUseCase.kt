package exampleApp.useCases.taskManage

import exampleApp.entities.task.TaskEntity

interface TaskCompleteUseCase {
    suspend fun handle(input: Input): Output

    class Input(val id: TaskEntity.Id)

    sealed interface Output {
        data object Completed : Output
        data object NotFound : Output
    }
}
