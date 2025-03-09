package exampleApp.useCases.taskManage

import exampleApp.entities.task.TaskEntity
import exampleApp.entities.task.TaskEntityWriter
import org.springframework.stereotype.Service

@Service
internal class TaskCompleteInteractor(
    private val taskEntityWriter: TaskEntityWriter,
) : TaskCompleteUseCase {
    override suspend fun handle(input: TaskCompleteUseCase.Input): TaskCompleteUseCase.Output {
        val updateCount = taskEntityWriter.updateCompleted(input.id, TaskEntity.Data.Completed(true))

        return when (updateCount) {
            0L -> TaskCompleteUseCase.Output.NotFound
            1L -> TaskCompleteUseCase.Output.Completed
            else -> throw IllegalStateException("Unexpected update count: $updateCount")
        }
    }
}
