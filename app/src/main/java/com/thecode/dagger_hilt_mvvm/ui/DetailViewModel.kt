package com.thecode.dagger_hilt_mvvm.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.thecode.dagger_hilt_mvvm.model.Blog
import com.thecode.dagger_hilt_mvvm.model.Detail
import com.thecode.dagger_hilt_mvvm.model.Post
import com.thecode.dagger_hilt_mvvm.repository.DetailRepository
import com.thecode.dagger_hilt_mvvm.repository.MainRepository
import com.thecode.dagger_hilt_mvvm.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailViewModel
@ViewModelInject
constructor(
    private val detailRepository: DetailRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<Detail>>> = MutableLiveData()


    val dataState: LiveData<DataState<List<Detail>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: DetailStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is DetailStateEvent.GetDetailEvents -> {
                    detailRepository.getDetail()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is DetailStateEvent.None -> {
                    // No action
                }
            }
        }
    }
}

sealed class DetailStateEvent {
    object GetDetailEvents: DetailStateEvent()
    object None : DetailStateEvent()

}

/*
sealed class MainStateEvent {
    object GetPostEvents : MainStateEvent()
    object None : MainStateEvent()
}*/

