package exampleApp.entities.task

interface TaskEntityReader {
    suspend fun getOrNull(id: TaskEntity.Id): TaskEntity?
}
