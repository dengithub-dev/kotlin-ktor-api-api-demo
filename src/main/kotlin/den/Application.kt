package den

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import den.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        install(ContentNegotiation) {
            json()
        }
    }.start(wait = true)
}
