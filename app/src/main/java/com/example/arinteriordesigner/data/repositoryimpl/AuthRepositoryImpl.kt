package com.example.arinteriordesigner.data.repositoryimpl

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.remember
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.example.arinteriordesigner.R
import com.example.arinteriordesigner.domain.repository.AuthRepository
import com.example.arinteriordesigner.domain.viewmodel.AuthResultState
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    private val loginManager = LoginManager.getInstance()
    val callbackManager = CallbackManager.Factory.create()
    private val _result = MutableSharedFlow<AuthResultState>(
        replay = 1, extraBufferCapacity = 1
    )
    override val facebookLoginResult: SharedFlow<AuthResultState> = _result

    init {
        loginManager.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    val credential = FacebookAuthProvider.getCredential(result.accessToken.token)
                    emitSafely(AuthResultState.Success(credential))
                }

                override fun onCancel() {
                    emitSafely(AuthResultState.Error("Đăng nhập Facebook đã bị hủy"))
                    //emitSafely(AuthResultState.LoggedOut)
                }

                override fun onError(error: FacebookException) {
                    emitSafely(AuthResultState.Error("Lỗi đăng nhập Facebook: ${error.message}"))
                    //emitSafely(AuthResultState.LoggedOut)
                    Log.e("facebook-login-failed", error.message ?: "")
                }
            }
        )
    }

    override suspend fun getGoogleIdToken(context: Context): Result<String> {
        return try {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .setAutoSelectEnabled(false)
                .setNonce(setNonce())
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val credentialManager = CredentialManager.create(context)
            val result = credentialManager.getCredential(context, request)

            val credential = result.credential
            val googleIdTokenCredential =
                GoogleIdTokenCredential.createFrom(credential.data)

            Result.success(googleIdTokenCredential.idToken)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser> =
        suspendCancellableCoroutine { cont ->
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        if (user != null) {
                            cont.resume(Result.success(user)) { cause, _, _ -> }
                        } else {
                            cont.resume(Result.failure(Exception("User is null"))) { cause, _, _ -> }
                        }
                    } else {
                        cont.resume(
                            Result.failure(
                                task.exception ?: Exception("Sign-in failed")
                            )
                        ) { cause, _, _ -> }
                    }
                }
        }

    override fun signInWithFacebook(activity: Activity) {
        emitSafely(AuthResultState.Loading)
        loginManager.logInWithReadPermissions(activity, listOf("public_profile", "email"))
    }

    override fun handleFacebookActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun emitSafely(state: AuthResultState) {
        _result.tryEmit(state)
    }

    fun setNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)

        return digest.fold("") { str, it ->
            str + "%02x".format(it)
        }
    }
}