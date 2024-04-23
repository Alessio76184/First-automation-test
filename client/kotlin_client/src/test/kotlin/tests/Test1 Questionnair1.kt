package tests

import connectivity.ConnectionManager
import io.ktor.client.*
import kotlinx.serialization.json.JsonObject
import com.google.gson.*
import kotlinx.serialization.json.jsonPrimitive
import models.Question
import models.Questionnaire
import models.PersonalityType

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import util.DependencyProvider
import java.net.URL



class `TestQuestionnair1` {
    //Connecting everything to dependecies with HTTP, Connections, and URL to test
    //Private is used to keep information only withing this class
    private val dependencyProvider = DependencyProvider()
    private val client = dependencyProvider.getPreconfiguredClientWithLogging()
    private val connectionManager = ConnectionManager(client)
    private val response = connectionManager.make_get_request(URL("${ConnectionManager.BASE_URL}/${ConnectionManager.SUFFIX_VALID_QUESTIONNAIRE}"))


    @Test
    fun testGetQuestionnaireQuestions40() {
        val questionnaire = dependencyProvider.getGson().fromJson(response, Questionnaire::class.java)

        assertTrue(questionnaire.questions.size == 40, "40 Questions Found")
        assertFalse(questionnaire.questions.size < 40, "There is a correct number of questions")

        // This is to check the number of IDs, but it's not necessary in Kotlin because it serialises automatically.
        // assertEquals(40, questionnaire.questions.first().id)
        //questionnaire.questions.forEach{ question -> question.id
    }

    @Test
    fun testGetQuestionnaire1Status200() {
        val retrieveStatus = dependencyProvider.getGson().fromJson(response, com.google.gson.JsonObject::class.java).get("status")?.asInt

        assertTrue(retrieveStatus == 200)

    }

    @Test
    fun testGetQuestionnaire1ValuesMatch() {
        val json_data = dependencyProvider.getGson().fromJson(response, com.google.gson.JsonObject::class.java).getAsJsonArray("questions")

        json_data.forEach { questionElement ->
            val question = questionElement.asJsonObject

            assertTrue(question.has("id"))
            assertTrue(question.get("id").isJsonPrimitive && question.get("id").asJsonPrimitive.isNumber)

            assertTrue(question.has("title"))
            assertTrue(question.get("title").isJsonPrimitive && question.get("title").asJsonPrimitive.isString)

            assertTrue(question.has("answer"))
            assertTrue(question.get("answer").isJsonPrimitive && question.get("answer").asJsonPrimitive.isNumber)

            assertTrue(question.has("type"))
            assertTrue(question.get("type").isJsonPrimitive && question.get("type").asJsonPrimitive.isString)

            val typeValue = question.get("type").asString
            assertTrue(PersonalityType.values().any { it.name == typeValue })
        }

    }
}


//Test_URL_Questionnaire1 = make_get_call("questions/questionnaire1")
//
//class Tests_Questionnaire1_Body_and_Status_Codes(unittest.TestCase):
//    maxDiff = None
//
//def test_get_questionnaire_when_questionnaire1_then_status_code_200(self):
//self.assertEqual(Test_URL_Questionnaire1.status_code, 200)
//
//def test_get_questionnaire_when_questionnaire1_then_status_200(self):
//retrieve_status = Test_URL_Questionnaire1.json()
//status_check = retrieve_status.get("status")
//self.assertEqual(status_check, 200)
//
//def test_get_questionnaire_when_questionnaire1_then_key_and_values_match(self):
//json_data = Test_URL_Questionnaire1.json()
//for question in json_data["questions"]:
//self.assertIn("id", question)
//self.assertIsInstance(question["id"], int)
//self.assertIn("title", question)
//self.assertIsInstance(question["title"], str)
//self.assertIn("type", question)
//self.assertIsInstance(question["type"], str)