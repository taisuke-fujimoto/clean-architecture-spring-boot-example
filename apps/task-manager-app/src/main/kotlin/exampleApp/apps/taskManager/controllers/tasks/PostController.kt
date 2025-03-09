package exampleApp.apps.taskManager.controllers.tasks

import exampleApp.entities.task.TaskEntity
import exampleApp.useCases.taskManage.TaskAddUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
internal class PostController(
    private val taskAddUseCase: TaskAddUseCase,
) {
    @PostMapping("/tasks")
    suspend fun post(@RequestBody request: Request): Response {
        val input = TaskAddUseCase.Input(
            TaskEntity.Data(
                TaskEntity.Data.Title(request.title),
                TaskEntity.Data.Completed(false),
            ),
        )
        val output = taskAddUseCase.handle(input)

        return Response(id = output.data.id.value)
    }

    class Request(
        val title: String,
    )

    class Response(
        val id: Long,
    )
}
