package com.okan.ui.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.okan.repository.auth.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class AuthViewModel
@ViewModelInject
constructor(
    private val authRepository: AuthRepository
) : ViewModel() {


}

sealed class AuthStateEvent {

    data class Login(
        val email: String,
        val password: String
    ) : AuthStateEvent()

    object None : AuthStateEvent()

}