package exampleApp.entities.task

interface TaskEntityWriter {
    suspend fun saveNew(data: TaskEntity.Data): TaskEntity

    suspend fun updateCompleted(id: TaskEntity.Id, completed: TaskEntity.Data.Completed): Long
}
