package com.example.arinteriordesigner.domain.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arinteriordesigner.domain.repository.AuthRepository
import com.facebook.CallbackManager
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthResultState {
    object Idle : AuthResultState()
    object Loading : AuthResultState()
    object LoggedOut : AuthResultState()
    data class Success(val credential: AuthCredential) : AuthResultState()
    data class Error(val message: String) : AuthResultState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepo: AuthRepository) : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _user = MutableStateFlow<FirebaseUser?>(null)

    private val _message = MutableStateFlow<String>("")
    val message: MutableStateFlow<String> get() = _message

    private val _isLoginSuccess = MutableStateFlow<Boolean>(false)
    val isLoginSuccess: MutableStateFlow<Boolean> get() = _isLoginSuccess
    private val callbackManager = CallbackManager.Factory.create()

    init {
        viewModelScope.launch {
            authRepo.facebookLoginResult.collect { state ->
                when (state) {
                    is AuthResultState.Idle -> {
                    }

                    is AuthResultState.Loading -> {
                        _message.emit("Đang đăng nhập Facebook…")
                    }

                    is AuthResultState.LoggedOut -> {
                        _isLoginSuccess.emit(false)
                        _user.emit(null)
                    }

                    is AuthResultState.Error -> {
                        _message.emit(state.message)
                        _isLoginSuccess.emit(false)
                    }

                    is AuthResultState.Success -> {
                        firebaseAuthWithCredential(state.credential)
                    }
                }
            }
        }
    }

    private fun firebaseAuthWithCredential(credential: AuthCredential) {
        viewModelScope.launch {
            try {
                firebaseAuth.signInWithCredential(credential)
                _message.emit("Đăng nhập thành công")
                _isLoginSuccess.emit(true)
            } catch (e: Exception) {
                _message.emit("Đăng nhập thất bại: ${e.message}")
                _isLoginSuccess.emit(false)
                Log.e("auth-with-credential-failed", e.message ?: "")
            }
        }
    }

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
        Log.e("Hehehehehe", "Hahahaha")
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

    fun signInWithFacebook(activity: Activity) {
        authRepo.signInWithFacebook(activity)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        authRepo.handleFacebookActivityResult(0, resultCode, data)
        callbackManager.onActivityResult(0, resultCode, data)
    }
}
