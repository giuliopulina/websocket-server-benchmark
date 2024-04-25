package com.example

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.routing() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriodMillis = 0 // FIXME: disabled, unfortunately ping from server to client always results in a timeout
        masking = false
    }
    routing {
        webSocket("/ktor") {
            for (frame in incoming) {
                frame as? Frame.Text ?: continue
                val userMessage = frame.readText()
                send(userMessage)
            }
        }
    }
}


