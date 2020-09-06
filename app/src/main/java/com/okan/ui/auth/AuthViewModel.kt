package com.okan.ui.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okan.repository.auth.AuthRepository
import com.okan.ui.auth.AuthStateEvent.Login
import com.okan.ui.auth.AuthStateEvent.None
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class AuthViewModel
@ViewModelInject
constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<Boolean> = MutableLiveData()

    val dataState: LiveData<Boolean>
        get() = _dataState

    fun setStateEvent(authStateEvent: AuthStateEvent) {
        viewModelScope.launch {
            when (authStateEvent) {
                is Login -> {
                    authRepository.loginWithEmail(
                        email = authStateEvent.email,
                        password = authStateEvent.password
                    )
                }

                is None -> {

                }
            }
        }
    }

}

sealed class AuthStateEvent {

    data class Login(
        val email: String,
        val password: String
    ) : AuthStateEvent()

    object None : AuthStateEvent()

}