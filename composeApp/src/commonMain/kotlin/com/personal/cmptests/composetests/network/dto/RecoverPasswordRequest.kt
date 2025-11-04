package com.personal.cmptests.composetests.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecoverPasswordRequest(val email: String)