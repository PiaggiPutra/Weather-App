package id.android.codebase.data.repository.utils

import androidx.annotation.WorkerThread
import id.android.codebase.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response


fun <RequestType, ResultType> networkBoundHandling(
    callApi: suspend () -> Response<RequestType>,
    processResponse: (response: RequestType?) -> ResultType?,
    handlerErrorResult: (suspend () -> Unit)? = null,
    saveFetchResult: suspend (ResultType?) -> Unit,
    @WorkerThread databaseQuery: (suspend () -> Flow<ResultType>?),
    onLoading: (() -> Unit)? = null,
): Flow<Resource<ResultType>> = flow {
    onLoading?.invoke()
    emit(Resource.loading(null))
    val result = withContext(Dispatchers.IO) {
        databaseQuery.invoke()
    }?.first()
    emit(Resource.loading(result))
    tryToConnect(
        callApi = callApi,
        processResponse = processResponse,
        onSuccess = { data -> Resource.success(data) },
    )?.let { resource ->
        when (resource.status) {
            Resource.Status.LOADING,
            Resource.Status.SUCCESS,
            -> {
                withContext(Dispatchers.IO) {
                    saveFetchResult(resource.data)
                }
                val dbResult = withContext(Dispatchers.IO) {
                    databaseQuery.invoke()
                }
                dbResult?.let { result ->
                    emit(Resource.success(result.first()))
                } ?: run {
                    emit(Resource.success(result))
                }
            }

            Resource.Status.ERROR -> {
                handlerErrorResult?.let { it() }
                emit(Resource.error(resource.error!!, result))
            }
        }
    }
}

suspend fun <RequestType, ResultType, ResourceType> tryToConnect(
    callApi: suspend () -> Response<RequestType>,
    processResponse: (response: RequestType?) -> ResultType?,
    onSuccess: (data: ResultType?) -> ResourceType?,
): ResourceType? {

    return try {
        val requestResult = callApi.invoke()
        with(requestResult) {
            val result = processResponse(body())
            onSuccess(result)
        }
    } catch (e:Exception){
        onSuccess(null)
    }



}