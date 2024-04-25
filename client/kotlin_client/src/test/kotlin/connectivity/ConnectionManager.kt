package connectivity

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import java.net.URL
import javax.swing.text.AbstractDocument.Content

class ConnectionManager(private val client: HttpClient) {

    fun make_get_request(url: URL): String {
        return runBlocking {
            val response = client.get(url)
            response.bodyAsText()
        }
    }

    fun make_post_request(url: URL, body: String): String {
        return runBlocking {
            val response = client.post(url) {
                setBody(body)
                contentType(ContentType.Any)
            }
            response.bodyAsText()
        }
    }

    companion object {
        const val BASE_URL = "http://localhost:8080"
        const val SUFFIX_VALID_QUESTIONNAIRE = "questions/questionnaire1"
        const val SUFFIX_INVALID_QUESTIONNAIRE = "questions/questionnaire0"
        const val SUFFIX_VALID_SUMBIT_ANSWER = "questionnaire/submit/answer"
    }
}
