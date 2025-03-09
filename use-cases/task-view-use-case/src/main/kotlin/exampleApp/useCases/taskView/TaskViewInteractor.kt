package exampleApp.useCases.taskView

import org.springframework.stereotype.Service

@Service
internal class TaskViewInteractor(
    private val query: TaskViewQuery,
) : TaskViewUseCase {
    override suspend fun handle(input: TaskViewUseCase.Input): TaskViewUseCase.Output =
        TaskViewUseCase.Output(
            data = query.query(
                input.filter.let {
                    TaskViewQuery.Filter(completed = it.completed)
                },
            ),
        )
}
