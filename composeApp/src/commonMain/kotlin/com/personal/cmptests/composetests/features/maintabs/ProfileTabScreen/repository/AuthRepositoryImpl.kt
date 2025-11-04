package com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.repository

import com.personal.cmptests.composetests.network.apiservices.ApiService
import com.personal.cmptests.composetests.network.dto.ApiResult
import com.personal.cmptests.composetests.network.dto.AuthResponse
import com.personal.cmptests.composetests.network.dto.LocalDataSource
import com.personal.cmptests.composetests.network.dto.User

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) : AuthRepository {

    override suspend fun signIn(email: String, password: String): ApiResult<User> {
        return try {
            val response: AuthResponse = apiService.signIn(email, password)
            val user = User(
                id = response.user.id,
                email = response.user.email,
                name = response.user.name,
                token = response.token
            )
            localDataSource.saveUser(user)
            ApiResult.Success(user)
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Sign in failed")
        }
    }

    override suspend fun signUp(email: String, password: String, name: String): ApiResult<User> {
        return try {
            val response: AuthResponse = apiService.signUp(email, password, name)
            val user = User(
                id = response.user.id,
                email = response.user.email,
                name = response.user.name,
                token = response.token
            )
            localDataSource.saveUser(user)
            ApiResult.Success(user)
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Sign up failed")
        }
    }

    override suspend fun verifyEmail(token: String): ApiResult<Boolean> {
        return try {
            val result = apiService.verifyEmail(token)
            ApiResult.Success(result)
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Email verification failed")
        }
    }

    override suspend fun recoverPassword(email: String): ApiResult<Boolean> {
        return try {
            val result = apiService.recoverPassword(email)
            ApiResult.Success(result)
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Password recovery failed")
        }
    }

    override suspend fun logout(): Boolean {
        localDataSource.clearUser()
        return true
    }

    override fun getCurrentUser(): User? {
        return localDataSource.getCurrentUser()
    }

    override fun isLoggedIn(): Boolean {
        return localDataSource.getCurrentUser() != null
    }
}
