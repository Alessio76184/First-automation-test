package com.alessio

import com.alessio.plugins.configureHTTP
import com.alessio.plugins.configureRouting
import com.alessio.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureRouting()
}