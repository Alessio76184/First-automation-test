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


}



//test_entry_type_change = {
//    "questionnaireId": 1,
//    "userId": "theman234",
//    "answeredQuestions": [
//    {"id": 0, "type": "Not Normal", "answer": 1},
//    ]
//}
//
//test_entry_expected = {
//    "questionnaireId": 1,
//    "userId": "UserId",
//    "answeredQuestions": [
//    {"id": 0, "type": "Unbreakable", "answer": 5},
//    ]
//}
//
//Test_URL_Questionnaire_Submit_Wrong_Type = make_post_call("questionnaire/submit/answer", test_entry_type_change)
//Test_URL_Questionnaire_Submit_Correct_Body = make_post_call("questionnaire/submit/answer", test_entry_expected)
//
//class Tests_Submit_Answer_Body_Status_and_Inputs(unittest.TestCase):
//    maxDiff = None
//
//def test_post_questionnaire_when_submit_wrong_ID_then_errorCode_105(self):
//retrieve_error = Test_URL_Questionnaire_Submit_Wrong_Questionnair_ID.json()
//error_check = retrieve_error.get("errorCode")
//error_message = retrieve_error.get("developerMessage")
//self.assertEqual(error_check, 105)
//self.assertIsNotNone(error_message)
//
//def test_post_questionnaire_when_submit_wrong_ID_then_status_code_200(self):
//self.assertEqual(Test_URL_Questionnaire_Submit_Wrong_Questionnair_ID.status_code, 200)
//
//def test_post_questionnaire_when_submit_wrong_ID_then_key_and_values_match(self):
//json_data_Q2 = Test_URL_Questionnaire_Submit_Wrong_Questionnair_ID.json()
//self.assertIn("questionnaireId", json_data_Q2)
//self.assertIsInstance(["questionnairId"], int)
//self.assertIn("userId", json_data_Q2)
//self.assertIsInstance(["userId"], str)
//for answer in json_data_Q2["answeredQuestions"]:
//self.assertIn("id", answer)
//self.assertIsInstance(answer["id"], int)
//self.assertIn("type", answer)
//self.assertIsInstance(answer["type"], str)
//self.assertIn("answer", answer)
//self.assertIsInstance(answer["answer"], int)
//
//def test_post_questionnaire_when_submit_wrong_type_then_errorCode_104(self):
//retrieve_error = Test_URL_Questionnaire_Submit_Wrong_Type.json()
//error_check = retrieve_error.get("errorCode")
//error_message = retrieve_error.get("developerMessage")
//self.assertEqual(error_check, 104)
//self.assertIsNotNone(error_message)
//
//def test_post_questionnaire_when_submit_wrong_type_then_status_code_200(self):
//self.assertEqual(Test_URL_Questionnaire_Submit_Wrong_Type.status_code, 200)
//
//def test_post_questionnaire_when_submit_wrong_type_then_key_and_values_match(self):
//json_data_Q2 = Test_URL_Questionnaire_Submit_Wrong_Type.json()
//self.assertIn("questionnaireId", json_data_Q2)
//self.assertIsInstance(["questionnairId"], int)
//self.assertIn("userId", json_data_Q2)
//self.assertIsInstance(["userId"], str)
//for answer in json_data_Q2["answeredQuestions"]:
//self.assertIn("id", answer)
//self.assertIsInstance(answer["id"], int)
//self.assertIn("type", answer)
//self.assertIsInstance(answer["type"], str)
//self.assertIn("answer", answer)
//self.assertIsInstance(answer["answer"], int)
//
//def test_post_questionnaire_when_submit_correct_body_then_errorCode_106(self):
//retrieve_error = Test_URL_Questionnaire_Submit_Correct_Body.json()
//error_check = retrieve_error.get("errorCode")
//error_message = retrieve_error.get("developerMessage")
//self.assertEqual(error_check, 106)
//self.assertIsNotNone(error_message)
//
//def test_post_questionnaire_when_submit_correct_body_then_status_code_200(self):
//self.assertEqual(Test_URL_Questionnaire_Submit_Correct_Body.status_code, 200)
//
//def test_post_questionnaire_when_submit_correct_body_then_key_and_values_match(self):
//json_data_Q2 = Test_URL_Questionnaire_Submit_Correct_Body.json()
//self.assertIn("questionnaireId", json_data_Q2)
//self.assertIsInstance(["questionnairId"], int)
//self.assertIn("userId", json_data_Q2)
//self.assertIsInstance(["userId"], str)
//for answer in json_data_Q2["answeredQuestions"]:
//self.assertIn("id", answer)
//self.assertIsInstance(answer["id"], int)
//self.assertIn("type", answer)
//self.assertIsInstance(answer["type"], str)
//self.assertIn("answer", answer)
//self.assertIsInstance(answer["answer"], int)
