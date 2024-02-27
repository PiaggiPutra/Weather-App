package id.android.codebase.features.login

import androidx.lifecycle.viewModelScope
import id.android.codebase.common.base.BaseViewModel
import id.android.codebase.data.repository.ExampleRepository
import id.android.codebase.data.repository.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(private val exampleRepository: ExampleRepository) : BaseViewModel() {

    fun getData() {
        viewModelScope.launch {
            exampleRepository.getExample().collect {
                when (it.status) {
                    Resource.Status.ERROR -> {
                        val error = it.error
                        println(error)
                        // handle error
                    }
                    Resource.Status.SUCCESS -> {
                        val data = it.data
                        println(data)
                        // handle success
                    }
                    else -> {
                        // handle else
                    }
                }
            }
        }
    }

}
