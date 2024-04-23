package connectivity

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import java.net.URL

class ConnectionManager(private val client: HttpClient) {

    fun make_get_request(url: URL): String {
        return runBlocking {
            val response = client.get(url)
            response.bodyAsText()
        }
    }

    fun make_post_request(url: URL, body: String) {
        return runBlocking {
            val response = client.post<HttpResponse>(url){
                body = body
            }
            response.recieve<String>()
        }
    }

    companion object {
        const val BASE_URL = "http://localhost:8080"
        const val SUFFIX_VALID_QUESTIONNAIRE = "questions/questionnaire1"
        const val SUFFIX_INVALID_QUESTIONNAIRE = "questions/questionnaire0"
        const val SUFFIX_VALID_SUMBIT_ANSWER = "questionnaire/submit/answer"
    }
}