package com.okan.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*

import com.okan.model.Coin
import com.okan.repository.MainRepository
import com.okan.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Coin>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Coin>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetCoinListEvent -> {
                    mainRepository.getCoinList().onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }

                is MainStateEvent.None -> {
                    // do nothing
                }
            }
        }
    }

}

sealed class MainStateEvent {

    object GetCoinListEvent : MainStateEvent()

    object None : MainStateEvent()

}