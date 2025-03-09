package exampleApp.useCases.taskManage

import exampleApp.entities.task.TaskEntity

interface TaskAddUseCase {
    suspend fun handle(input: Input): Output

    class Input(val data: TaskEntity.Data)

    class Output(val data: TaskEntity)
}
