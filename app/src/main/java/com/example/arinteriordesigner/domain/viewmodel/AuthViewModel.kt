package com.example.arinteriordesigner.domain.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arinteriordesigner.domain.repository.AuthRepository
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepo: AuthRepository) : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user: StateFlow<FirebaseUser?> get() = _user

    private val _message = MutableStateFlow<String>("")
    val message: MutableStateFlow<String> get() = _message

    private val _isLoginSuccess = MutableStateFlow<Boolean>(false)
    val isLoginSuccess: MutableStateFlow<Boolean> get() = _isLoginSuccess

    private fun firebaseAuthWithGoogle(idToken: String) {
        viewModelScope.launch {
            val result = authRepo.signInWithGoogle(idToken)
            result.fold(
                { value ->
                    _message.emit("Sign in successful")
                    _isLoginSuccess.emit(true)
                },
                { e ->
                    _message.emit("Sign in failed")
                    _isLoginSuccess.emit(false)
                    Log.e("google-login-failed", e.message ?: "")
                }
            )
        }
    }

    fun signInWithGoogle(context: Context) {
        viewModelScope.launch {
            val tokenResult = authRepo.getGoogleIdToken(context)
            tokenResult.fold(
                {
                    val idToken = tokenResult.getOrNull()
                    if (idToken != null) {
                        firebaseAuthWithGoogle(idToken)
                    }
                },
                { e ->
                    Log.e("Hehehehehe", e.message ?: "Hahahaha")
                }
            )
        }
    }
}
