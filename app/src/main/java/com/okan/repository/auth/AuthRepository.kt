package com.okan.repository.auth

import com.google.firebase.auth.FirebaseAuth

class AuthRepository
constructor(
    private val firebaseAuth: FirebaseAuth
) {
    suspend fun loginWithEmail(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {


                } else {

                }
            }
    }

}