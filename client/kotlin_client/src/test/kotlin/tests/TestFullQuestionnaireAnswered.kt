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


class TestFullQuestionnaireAnswered {

    private val dependencyProvider = DependencyProvider()
    private val client = dependencyProvider.getPreconfiguredClientWithLogging()
    private val connectionManager = ConnectionManager(client)

    val testFullQuestionnaireValid = Answers(1,"UserId", listOf(
        AnsweredQuestion(0, PersonalityType.Unbreakable, 10),
        AnsweredQuestion(1, PersonalityType.Rejected, 5),
        AnsweredQuestion(2, PersonalityType.Rejected, 5),
        AnsweredQuestion(3, PersonalityType.Conformer, 5),
        AnsweredQuestion(4, PersonalityType.Inspector, 5),
        AnsweredQuestion(5, PersonalityType.Rejected, 5),
        AnsweredQuestion(6, PersonalityType.Doer, 5),
        AnsweredQuestion(7, PersonalityType.Inspector, 5),
        AnsweredQuestion(8, PersonalityType.Unbreakable, 10),
        AnsweredQuestion(9, PersonalityType.Savior, 5),
        AnsweredQuestion(10, PersonalityType.Dreamer, 5),
        AnsweredQuestion(11, PersonalityType.Dreamer, 5),
        AnsweredQuestion(12, PersonalityType.Conformer, 5),
        AnsweredQuestion(13, PersonalityType.Doer, 5),
        AnsweredQuestion(14, PersonalityType.Savior, 5),
        AnsweredQuestion(15, PersonalityType.Pessimist, 5),
        AnsweredQuestion(16, PersonalityType.Inspector, 5),
        AnsweredQuestion(17, PersonalityType.Conformer, 5),
        AnsweredQuestion(18, PersonalityType.Conformer, 5),
        AnsweredQuestion(19, PersonalityType.Savior, 5),
        AnsweredQuestion(20, PersonalityType.Dreamer, 5),
        AnsweredQuestion(21, PersonalityType.Doer, 5),
        AnsweredQuestion(22, PersonalityType.Savior, 5),
        AnsweredQuestion(23, PersonalityType.Savior, 5),
        AnsweredQuestion(24, PersonalityType.Pessimist, 5),
        AnsweredQuestion(25, PersonalityType.Doer, 5),
        AnsweredQuestion(26, PersonalityType.Pessimist, 5),
        AnsweredQuestion(27, PersonalityType.Unbreakable, 5),
        AnsweredQuestion(28, PersonalityType.Dreamer, 5),
        AnsweredQuestion(29, PersonalityType.Inspector, 5),
        AnsweredQuestion(30, PersonalityType.Doer, 5),
        AnsweredQuestion(31, PersonalityType.Rejected, 5),
        AnsweredQuestion(32, PersonalityType.Unbreakable, 5),
        AnsweredQuestion(33, PersonalityType.Pessimist, 5),
        AnsweredQuestion(34, PersonalityType.Conformer, 5),
        AnsweredQuestion(35, PersonalityType.Inspector, 5),
        AnsweredQuestion(36, PersonalityType.Unbreakable, 5),
        AnsweredQuestion(37, PersonalityType.Dreamer, 5),
        AnsweredQuestion(38, PersonalityType.Pessimist, 5),
        AnsweredQuestion(39, PersonalityType.Rejected, 5)
    ))

    val SubmitValidFullQuestionnaire = connectionManager.make_post_request(URL("${ConnectionManager.BASE_URL}/${ConnectionManager.SUFFIX_VALID_SUMBIT_ANSWER}"), Gson().toJson(testFullQuestionnaireValid))

    @Test
    fun testSubmitAnswer(){

    }



}