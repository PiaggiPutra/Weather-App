package id.android.codebase.data.remote

import id.android.codebase.data.model.ExampleModel
import retrofit2.Response
import retrofit2.http.GET

interface ExampleService {

    @GET("get/example")
    suspend fun fetchExampleAsync(): Response<ExampleModel>

}
