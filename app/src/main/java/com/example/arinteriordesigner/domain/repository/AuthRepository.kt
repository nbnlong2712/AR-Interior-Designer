package com.example.arinteriordesigner.domain.repository

import android.content.Context
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun getGoogleIdToken(context: Context): Result<String>
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser>
}