package tests

import com.google.gson.Gson
import com.google.gson.JsonObject
import connectivity.ConnectionManager
import io.ktor.client.call.*
import io.ktor.client.statement.*
import models.AnsweredQuestion
import models.Answers
import models.ErrorMessage
import models.PersonalityType
import util.DependencyProvider
import java.net.URL
import kotlin.test.*


class `TestQuestionnaireSubmitAnswer` {

    private val dependencyProvider = DependencyProvider()
    private val client = dependencyProvider.getPreconfiguredClientWithLogging()
    private val connectionManager = ConnectionManager(client)

    val testEntryQuestion50 = Answers(50,"UserId", listOf(AnsweredQuestion(0,PersonalityType.Unbreakable,5)))
    val submitWrongAnswer105Response = connectionManager.make_post_request(URL("${ConnectionManager.BASE_URL}/${ConnectionManager.SUFFIX_VALID_SUMBIT_ANSWER}"), Gson().toJson(testEntryQuestion50))

    val testExpectedEntry = Answers(1, "UserID", listOf(AnsweredQuestion(0,PersonalityType.Unbreakable, 5)))
    val submitRightAnswer106Response = connectionManager.make_post_request(URL("${ConnectionManager.BASE_URL}/${ConnectionManager.SUFFIX_VALID_SUMBIT_ANSWER}"), Gson().toJson(testExpectedEntry))


    @Test
    fun testPostSubmitWrongIDthenErrorCode105(){

        // Retriving the information from the post
        val retrieveError = Gson().fromJson(submitWrongAnswer105Response, ErrorMessage::class.java)

        // Checks that the error code is the correct number & checks if there is an error message and prints it as well
        // The print is also for debugging purposes
        assertTrue { retrieveError.errorCode == 105 }
        assertNotNull(retrieveError.developerMessage)
        println("${retrieveError.developerMessage}")
    }

    @Test
    fun testPostSumbitRightAnswerErrorCode106(){
        val retrieveError = Gson().fromJson(submitRightAnswer106Response, ErrorMessage::class.java)

        assertTrue { retrieveError.errorCode == 106 }
        assertNotNull(retrieveError.developerMessage)
        println("${retrieveError.developerMessage}")
    }

}