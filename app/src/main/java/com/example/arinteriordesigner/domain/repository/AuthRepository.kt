package com.example.arinteriordesigner.domain.repository

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.arinteriordesigner.domain.viewmodel.AuthResultState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.SharedFlow

interface AuthRepository {
    suspend fun getGoogleIdToken(context: Context): Result<String>
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser>
    fun signInWithFacebook(activity: Activity)
    fun handleFacebookActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    val facebookLoginResult: SharedFlow<AuthResultState>
}