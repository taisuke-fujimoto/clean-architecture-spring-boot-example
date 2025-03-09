package exampleApp.useCases.taskManage

import exampleApp.entities.task.TaskEntity

interface TaskGetUseCase {
    suspend fun handle(input: Input): Output

    class Input(val id: TaskEntity.Id)

    sealed interface Output {
        class Found(val data: TaskEntity) : Output

        data object NotFound : Output
    }
}
