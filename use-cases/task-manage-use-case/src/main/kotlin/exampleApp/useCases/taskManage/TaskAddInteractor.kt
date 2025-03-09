package exampleApp.useCases.taskManage

import exampleApp.entities.task.TaskEntityWriter
import org.springframework.stereotype.Service

@Service
internal class TaskAddInteractor(
    private val taskEntityWriter: TaskEntityWriter,
) : TaskAddUseCase {
    override suspend fun handle(input: TaskAddUseCase.Input): TaskAddUseCase.Output =
        TaskAddUseCase.Output(
            taskEntityWriter.saveNew(input.data),
        )
}
