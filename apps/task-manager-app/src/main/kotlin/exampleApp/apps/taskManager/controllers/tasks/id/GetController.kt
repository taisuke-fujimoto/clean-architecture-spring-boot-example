package exampleApp.apps.taskManager.controllers.tasks.id

import exampleApp.entities.task.TaskEntity
import exampleApp.useCases.taskManage.TaskGetUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
internal class GetController(
    private val taskGetUseCase: TaskGetUseCase,
) {
    @GetMapping("/tasks/{id}")
    suspend fun get(@PathVariable id: Long): ResponseEntity<Response> {
        val input = TaskGetUseCase.Input(TaskEntity.Id(id))

        return when (val output = taskGetUseCase.handle(input)) {
            is TaskGetUseCase.Output.Found ->
                output.data.toResponse().let { ResponseEntity.ok(it) }

            is TaskGetUseCase.Output.NotFound ->
                ResponseEntity.notFound().build()
        }
    }

    class Response(
        val id: Long,
        val createdAt: LocalDateTime,
        val title: String,
        val completed: Boolean,
    )

    private fun TaskEntity.toResponse(): Response =
        Response(
            id = id.value,
            createdAt = createdAt,
            title = data.title.value,
            completed = data.completed.value,
        )
}
