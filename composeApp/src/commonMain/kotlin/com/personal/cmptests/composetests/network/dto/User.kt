package com.personal.cmptests.composetests.network.dto

data class User(
    val id: String,
    val email: String,
    val name: String,
    val token: String? = null
)