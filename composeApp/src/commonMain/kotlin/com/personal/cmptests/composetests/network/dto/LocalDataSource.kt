package com.personal.cmptests.composetests.network.dto

class LocalDataSource {
    private var currentUser: User? = null

    fun saveUser(user: User) {
        currentUser = user
        // For simple in-memory storage, this works across all platforms
    }

    fun getCurrentUser(): User? = currentUser

    fun clearUser() {
        currentUser = null
    }
}