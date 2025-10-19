package com.personal.cmptests.composetests

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform