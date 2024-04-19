package tests

import connectivity.ConnectionManager
import io.ktor.client.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.net.URL

class `TestQuestionnair1` {

    private val client = HttpClient()
    private val connectionManager = ConnectionManager(client)

    private fun countQuestionnaireIds(json: String): Int {
        val jsonQuestions = jsonToMap(json)["questions"] as? List<Map<String, Any>> ?: return 0
        return jsonQuestions.count { it["id"] != null }
    }

    @Test
    fun testGetQuestionnaireIdCount() {
        val response = connectionManager.make_get_request(URL("${ConnectionManager.BASE_URL}/${ConnectionManager.SUFFIX_VALID_QUESTIONNAIRE}"))
        val idCount = countQuestionnaireIds(response)
        assertEquals(40, idCount)
    }

    private fun jsonToMap(json: String): Map<String, Any> {
        return emptyMap()
    }


}


//Test_URL_Questionnaire1 = make_get_call("questions/questionnaire1")
//
//class Tests_Questionnaire1_Body_and_Status_Codes(unittest.TestCase):
//    maxDiff = None
//
//def test_get_questionnaire_when_questionnaire1_then_id_count_40(self):
//id_count = QuestionnaireIdCount(Test_URL_Questionnaire1)
//self.assertEqual(id_count, 40)
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