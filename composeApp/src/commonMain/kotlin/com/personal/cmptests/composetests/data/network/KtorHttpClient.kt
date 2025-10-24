package com.personal.cmptests.composetests.data.network


import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.plugins.logging.*
import kotlinx.serialization.json.Json


expect fun createHttpClient(): HttpClient

fun commonHttpClientConfig(builder: HttpClient.() -> Unit = {}): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }

        builder()
    }
}