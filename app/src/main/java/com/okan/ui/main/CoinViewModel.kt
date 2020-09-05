package com.okan.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*

import com.okan.model.Coin
import com.okan.repository.main.CoinListRepository
import com.okan.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val coinListRepository: CoinListRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Coin>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Coin>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetCoinListEvent -> {
                    coinListRepository.getCoinList().onEach { dataState ->
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