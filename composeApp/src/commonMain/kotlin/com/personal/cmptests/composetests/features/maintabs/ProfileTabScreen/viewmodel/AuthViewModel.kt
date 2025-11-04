package com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.viewmodel

import com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.repository.AuthRepository
import com.personal.cmptests.composetests.network.dto.ApiResult
import com.personal.cmptests.composetests.network.dto.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel(
    private val authRepository: AuthRepository
) {
    private val _authState = MutableStateFlow<ApiResult<User>?>(null)
    val authState: StateFlow<ApiResult<User>?> = _authState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _currentView = MutableStateFlow<AuthView>(AuthView.SignIn)
    val currentView: StateFlow<AuthView> = _currentView.asStateFlow()

    suspend fun signIn(email: String, password: String) {
        _isLoading.value = true
        _authState.value = ApiResult.Loading
        _authState.value = authRepository.signIn(email, password)
        _isLoading.value = false
    }

    suspend fun signUp(email: String, password: String, name: String) {
        _isLoading.value = true
        _authState.value = ApiResult.Loading
        _authState.value = authRepository.signUp(email, password, name)
        _isLoading.value = false
    }

    suspend fun verifyEmail(token: String): ApiResult<Boolean> {
        return authRepository.verifyEmail(token)
    }

    suspend fun recoverPassword(email: String): ApiResult<Boolean> {
        return authRepository.recoverPassword(email)
    }

    fun navigateToSignIn() {
        _currentView.value = AuthView.SignIn
        clearAuthState()
    }

    fun navigateToSignUp() {
        _currentView.value = AuthView.SignUp
        clearAuthState()
    }

    fun navigateToRecoverPassword() {
        _currentView.value = AuthView.RecoverPassword
        clearAuthState()
    }

    fun navigateToVerifyEmail() {
        _currentView.value = AuthView.VerifyEmail
        clearAuthState()
    }

    fun clearAuthState() {
        _authState.value = null
    }
}

sealed class AuthView {
    object SignIn : AuthView()
    object SignUp : AuthView()
    object RecoverPassword : AuthView()
    object VerifyEmail : AuthView()
}