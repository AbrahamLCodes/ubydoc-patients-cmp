package com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.repository

import com.personal.cmptests.composetests.network.dto.ApiResult
import com.personal.cmptests.composetests.network.dto.User

interface AuthRepository {
    suspend fun signIn(email: String, password: String): ApiResult<User>
    suspend fun signUp(email: String, password: String, name: String): ApiResult<User>
    suspend fun verifyEmail(token: String): ApiResult<Boolean>
    suspend fun recoverPassword(email: String): ApiResult<Boolean>
    suspend fun logout(): Boolean
    fun getCurrentUser(): User?
    fun isLoggedIn(): Boolean
}