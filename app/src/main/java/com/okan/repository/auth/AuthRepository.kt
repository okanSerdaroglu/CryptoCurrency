package com.okan.repository.auth

import com.google.firebase.auth.FirebaseAuth
import com.okan.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class AuthRepository
constructor(
    private val firebaseAuth: FirebaseAuth
) {
    suspend fun loginWithEmail(
        email: String,
        password: String
    ): Flow<DataState<Boolean>> = flow {

        emit(DataState.Loading)
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                GlobalScope.launch(Dispatchers.IO) {
                    if (task.isSuccessful) {
                        emit(DataState.Success(true))
                    } else {
                        emit(DataState.Error(task.exception!!))
                    }
                }
            }

    }

}