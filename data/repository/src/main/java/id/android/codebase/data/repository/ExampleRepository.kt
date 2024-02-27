package id.android.codebase.data.repository

import id.android.codebase.data.local.dao.ExampleDao
import id.android.codebase.data.model.ExampleModel
import id.android.codebase.data.remote.ExampleDatasource
import id.android.codebase.data.repository.utils.Resource
import id.android.codebase.data.repository.utils.getFlow
import kotlinx.coroutines.flow.Flow

interface ExampleRepository {
    suspend fun getExample(): Flow<Resource<ExampleModel>>
}

class ExampleRepositoryImpl(
    private val datasource: ExampleDatasource,
    private val dao: ExampleDao
) : ExampleRepository {

    override suspend fun getExample() = datasource.fetchTopUsersAsync().getFlow()

}
