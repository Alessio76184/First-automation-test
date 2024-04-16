package tests

import connectivity.ConnectionManager
import models.Questionnaire
import util.DependencyProvider
import java.net.URL
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ExampleTest {

    val dependencyProvider = DependencyProvider()
    val connectionManager = ConnectionManager(dependencyProvider.getPreconfiguredClientWithLogging())

    @Test
    fun get_about_expectAppVersionString() {
        val expectedResponse = "App version: null"
        val response = connectionManager.make_get_request(URL(GET_ABOUT_URL))

        assertEquals(expectedResponse, response)
    }

    @Test
    fun get_questions_validQuestionnaireId_returns_questionnaire() {
        val response = connectionManager.make_get_request(URL("${ConnectionManager.BASE_URL}/questions/questionnaire1"))
        val questionnaire = dependencyProvider.getGson().fromJson(response, Questionnaire::class.java)

        assertTrue(questionnaire.questions.size == 40)
        assertTrue(questionnaire.questions.first().title == "I take pride in being seen as reliable and ready for any circumstance.")
        assertEquals(43, questionnaire.questions.size)
    }

    @Test
    fun get_questions_invalidQuestionnaireId_returns_noQuestionnaireFoundErrorReceived() {
        val response = connectionManager.make_get_request(URL(GET_VALID_QUESTIONNAIRE_URL))
        // update class to be serialized into to match Error from server side
        // therefore add ServerError data class
        val questionnaire = dependencyProvider.getGson().fromJson(response, Questionnaire::class.java)

        // assert expected error for questions/questionnaire
    }

    companion object {
        private const val GET_ABOUT_URL = "${ConnectionManager.BASE_URL}/about"
        private const val GET_VALID_QUESTIONNAIRE_URL = "${ConnectionManager.BASE_URL}/questions/questionnaire1"
    }
}