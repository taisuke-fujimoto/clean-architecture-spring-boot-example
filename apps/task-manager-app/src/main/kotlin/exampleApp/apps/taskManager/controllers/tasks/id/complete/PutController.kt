package exampleApp.apps.taskManager.controllers.tasks.id.complete

import exampleApp.entities.task.TaskEntity
import exampleApp.useCases.taskManage.TaskCompleteUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PutController(
    private val taskCompleteUseCase: TaskCompleteUseCase,
) {
    @PutMapping("/tasks/{id}/complete")
    suspend fun put(@PathVariable id: Long): ResponseEntity<Nothing> {
        val input = TaskCompleteUseCase.Input(TaskEntity.Id(id))
        val output = taskCompleteUseCase.handle(input)

        return when (output) {
            is TaskCompleteUseCase.Output.Completed ->
                ResponseEntity.noContent().build()

            is TaskCompleteUseCase.Output.NotFound ->
                ResponseEntity.notFound().build()
        }
    }
}
