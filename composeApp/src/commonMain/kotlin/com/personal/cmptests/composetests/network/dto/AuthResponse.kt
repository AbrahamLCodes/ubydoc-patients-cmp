package com.personal.cmptests.composetests.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val user: UserResponse,
    val token: String
)