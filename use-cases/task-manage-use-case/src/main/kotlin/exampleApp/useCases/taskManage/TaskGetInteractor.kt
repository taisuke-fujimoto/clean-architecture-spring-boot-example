package exampleApp.useCases.taskManage

import exampleApp.entities.task.TaskEntityReader
import org.springframework.stereotype.Service

@Service
internal class TaskGetInteractor(
    private val taskEntityReader: TaskEntityReader,
) : TaskGetUseCase {
    override suspend fun handle(input: TaskGetUseCase.Input): TaskGetUseCase.Output {
        val taskEntity = taskEntityReader.getOrNull(input.id)

        return if (taskEntity == null) {
            TaskGetUseCase.Output.NotFound
        } else {
            TaskGetUseCase.Output.Found(taskEntity)
        }
    }
}
