package com.personal.cmptests.composetests.network.apiservices

import com.personal.cmptests.composetests.network.dto.AuthRequest
import com.personal.cmptests.composetests.network.dto.AuthResponse
import com.personal.cmptests.composetests.network.dto.RecoverPasswordRequest
import com.personal.cmptests.composetests.network.dto.VerifyEmailRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiService(private val client: HttpClient) {

    suspend fun signIn(email: String, password: String): AuthResponse {
        return client.post("/auth/signin") {
            contentType(ContentType.Application.Json)
            setBody(AuthRequest(email, password))
        }.body()
    }

    suspend fun signUp(email: String, password: String, name: String): AuthResponse {
        return client.post("/auth/signup") {
            contentType(ContentType.Application.Json)
            setBody(AuthRequest(email, password, name))
        }.body()
    }

    suspend fun verifyEmail(token: String): Boolean {
        return client.post("/auth/verify-email") {
            contentType(ContentType.Application.Json)
            setBody(VerifyEmailRequest(token))
        }.body()
    }

    suspend fun recoverPassword(email: String): Boolean {
        return client.post("/auth/recover-password") {
            contentType(ContentType.Application.Json)
            setBody(RecoverPasswordRequest(email))
        }.body()
    }
}