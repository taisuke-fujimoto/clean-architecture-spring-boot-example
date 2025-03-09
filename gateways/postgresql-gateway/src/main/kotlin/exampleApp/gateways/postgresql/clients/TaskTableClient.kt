package exampleApp.gateways.postgresql.clients

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.applyAndAwait
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.r2dbc.core.flow
import org.springframework.data.r2dbc.core.select
import org.springframework.data.r2dbc.core.update
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.Update
import org.springframework.stereotype.Component

@Component
internal class TaskTableClient(
    private val template: R2dbcEntityTemplate,
) {
    suspend fun selectOneById(id: Long): TaskRecord? = template
        .select<TaskRecord>()
        .matching(Query.query(Criteria.where("id").`is`(id)))
        .awaitOneOrNull()

    suspend fun insert(record: TaskRecord): TaskRecord = template
        .insert(record)
        .awaitSingle()

    suspend fun updateCompleted(id: Long, completed: Boolean): Long = template
        .update<TaskRecord>()
        .matching(Query.query(Criteria.where("id").`is`(id)))
        .applyAndAwait(
            Update.update("completed", completed),
        )

    suspend fun selectListOrderByCreatedAtDesc(completed: Boolean?): List<TaskRecord> {
        val criteria = Criteria.empty()
            .let {
                if (completed != null) {
                    it.and("completed").`is`(completed)
                } else {
                    it
                }
            }

        return template
            .select<TaskRecord>()
            .matching(
                Query.query(criteria)
                    .sort(Sort.by(Sort.Order.desc("created_at"))),
            )
            .flow()
            .toList()
    }
}
