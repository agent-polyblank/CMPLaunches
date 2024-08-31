package org.king.kmplaunches.module

import SpaceXAPI
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.king.kmplaunches.SpaceXSDK
import org.king.kmplaunches.viewmodel.LaunchesViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule =
    module {
        single {
            HttpClient {
                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        },
                    )
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }
        }
        single { SpaceXSDK(get(), get()) }
        single { SpaceXAPI(get()) }
        factory { LaunchesViewModel(get()) }
    }

expect val platformModules: Module
