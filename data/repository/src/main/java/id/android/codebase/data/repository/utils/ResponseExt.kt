package id.android.codebase.data.repository.utils

import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> Response<T>.getFlow() = flow {
    when {
        isSuccessful -> {
            if (body() != null) emit(Resource.success(body()))
            else {
                // or handle more
                emit(Resource.error(Throwable(errorBody()?.string()), body()))
            }
        }
        else -> {
            // or handle more
            emit(Resource.error(Throwable(errorBody()?.string()), null))
        }
    }
}
