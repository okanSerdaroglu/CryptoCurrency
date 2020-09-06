package com.okan.repository.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.okan.model.auth.LoginResult
import com.okan.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception

class AuthRepository
constructor(
    private val firebaseAuth: FirebaseAuth
) {
    init {
        Log.d("firebaseAuth",firebaseAuth.toString())
    }
   suspend fun loginWithEmail(
        email: String,
        password: String
    ): Flow<DataState<LoginResult>> = flow {
        emit(DataState.Loading)
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    GlobalScope.launch(Dispatchers.Main) {
                        if (task.isSuccessful) {
                            emit(
                                DataState.Success(
                                    LoginResult(
                                        task.isSuccessful
                                    )
                                )
                            )
                        } else {
                            emit(DataState.Error(task.exception!!))
                        }
                    }
                }
        }catch (e:Exception){
            emit(DataState.Error(e))
        }

    }

}