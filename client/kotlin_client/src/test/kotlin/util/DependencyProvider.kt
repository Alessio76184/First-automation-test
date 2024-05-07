package util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.logging.*

class DependencyProvider {

    private var httpClient: HttpClient? = null

    fun getPreconfiguredClientWithLogging(): HttpClient {
        if(httpClient != null) {
            return httpClient as HttpClient
        } else {
            httpClient = HttpClient(CIO.create()) {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }
            return httpClient as HttpClient
        }
    }

    fun getGson(): Gson {
        return GsonBuilder().setPrettyPrinting().create()
    }

}