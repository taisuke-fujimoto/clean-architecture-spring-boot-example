package xampleApp.apps.taskViewer.controllers.tasks

import exampleApp.entities.task.TaskEntity
import exampleApp.useCases.taskView.TaskViewUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class GetController(
    private val taskViewUseCase: TaskViewUseCase,
) {
    @GetMapping("/tasks")
    suspend fun get(@RequestParam(required = false) completed: Boolean?): Response {
        val input = TaskViewUseCase.Input(
            TaskViewUseCase.Input.Filter(
                completed = completed?.let(TaskEntity.Data::Completed),
            ),
        )
        val output = taskViewUseCase.handle(input)

        return Response(
            items = output.data.map { it.toItem() },
        )
    }

    class Response(
        val items: List<Item>,
    ) {
        class Item(
            val id: Long,
            val createdAt: LocalDateTime,
            val title: String,
            val completed: Boolean,
        )
    }

    private fun TaskEntity.toItem(): Response.Item =
        Response.Item(
            id = id.value,
            createdAt = createdAt,
            title = data.title.value,
            completed = data.completed.value,
        )
}
