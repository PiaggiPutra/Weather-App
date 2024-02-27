package id.android.codebase.data.remote

/**
 * implementation(of [ExampleService] interface)
 */
class ExampleDatasource(private val exampleService: ExampleService) {

    suspend fun fetchTopUsersAsync() = exampleService.fetchExampleAsync()

}
