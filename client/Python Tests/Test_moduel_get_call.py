import unittest
from connection_manager import make_get_call, QuestionnaireIdCount, make_post_call
from colorama import init, Fore

init(autoreset=True)

class ColorfulResult(unittest.TextTestResult):
    def addSuccess(self, test):
        super().addSuccess(test)
        print(Fore.GREEN + f"Success: {test.id()}")

    def addFailure(self, test, err):
        super().addFailure(test, err)
        print(Fore.RED + f"FAILIURE: {test.id()}")

    def addError(self, test, err):
        super().addError(test, err)
        print(Fore.RED + f"ERROR: {test.id()}")

#Test 1
## Checking for the number of questions in the questionnair, checking the status & status code, checking the question structures match Test

Test_URL_Questionnaire1 = make_get_call("questions/questionnaire1")

class Tests_Questionnaire1_Body_and_Status_Codes(unittest.TestCase):
    maxDiff = None

    def test_get_questionnaire_when_questionnaire1_then_id_count_40(self):
        id_count = QuestionnaireIdCount(Test_URL_Questionnaire1)
        self.assertEqual(id_count, 40)

    def test_get_questionnaire_when_questionnaire1_then_status_code_200(self):
        self.assertEqual(Test_URL_Questionnaire1.status_code, 200)
    
    def test_get_questionnaire_when_questionnaire1_then_status_200(self):
        retrieve_status = Test_URL_Questionnaire1.json()
        status_check = retrieve_status.get("status")
        self.assertEqual(status_check, 200)

    def test_get_questionnaire_when_questionnaire1_then_key_and_values_match(self):
        json_data = Test_URL_Questionnaire1.json()
        for question in json_data["questions"]:
            self.assertIn("id", question)
            self.assertIsInstance(question["id"], int)
            self.assertIn("title", question)
            self.assertIsInstance(question["title"], str)
            self.assertIn("type", question)
            self.assertIsInstance(question["type"], str)

# Test 2
## Checking if post function works for one question using structure provide, checking status & status code

test_entry_question_50 = {
    "questionnaireId": 50,
    "userId": "theman234",
    "answeredQuestions": [
        {"id": 0, "type": "Unbreakable", "answer": 1},
    ]
}

test_entry_type_change = {
    "questionnaireId": 1,
    "userId": "theman234",
    "answeredQuestions": [
        {"id": 0, "type": "Not Normal", "answer": 1},
    ]
}

test_entry_expected = {
    "questionnaireId": 1,
    "userId": "UserId",
    "answeredQuestions": [
        {"id": 0, "type": "Unbreakable", "answer": 5},
    ]
}

Test_URL_Questionnaire_Submit_Wrong_Questionnair_ID = make_post_call("questionnaire/submit/answer", test_entry_question_50)
Test_URL_Questionnaire_Submit_Wrong_Type = make_post_call("questionnaire/submit/answer", test_entry_type_change)
Test_URL_Questionnaire_Submit_Correct_Body = make_post_call("questionnaire/submit/answer", test_entry_expected)

class Tests_Submit_Answer_Body_Status_and_Inputs(unittest.TestCase):
    maxDiff = None
    
    def test_post_questionnaire_when_submit_wrong_ID_then_errorCode_105(self):
        retrieve_error = Test_URL_Questionnaire_Submit_Wrong_Questionnair_ID.json()
        error_check = retrieve_error.get("errorCode")
        error_message = retrieve_error.get("developerMessage")
        self.assertEqual(error_check, 105)
        self.assertIsNotNone(error_message)

    def test_post_questionnaire_when_submit_wrong_ID_then_status_code_200(self):
        self.assertEqual(Test_URL_Questionnaire_Submit_Wrong_Questionnair_ID.status_code, 200)
    
    def test_post_questionnaire_when_submit_wrong_ID_then_key_and_values_match(self):
        json_data_Q2 = Test_URL_Questionnaire_Submit_Wrong_Questionnair_ID.json()
        self.assertIn("questionnaireId", json_data_Q2)
        self.assertIsInstance(["questionnairId"], int)
        self.assertIn("userId", json_data_Q2)
        self.assertIsInstance(["userId"], str)
        for answer in json_data_Q2["answeredQuestions"]:
            self.assertIn("id", answer)
            self.assertIsInstance(answer["id"], int)
            self.assertIn("type", answer)
            self.assertIsInstance(answer["type"], str)
            self.assertIn("answer", answer)
            self.assertIsInstance(answer["answer"], int)    

    def test_post_questionnaire_when_submit_wrong_type_then_errorCode_104(self):
        retrieve_error = Test_URL_Questionnaire_Submit_Wrong_Type.json()
        error_check = retrieve_error.get("errorCode")
        error_message = retrieve_error.get("developerMessage")
        self.assertEqual(error_check, 104)
        self.assertIsNotNone(error_message)

    def test_post_questionnaire_when_submit_wrong_type_then_status_code_200(self):
        self.assertEqual(Test_URL_Questionnaire_Submit_Wrong_Type.status_code, 200)
    
    def test_post_questionnaire_when_submit_wrong_type_then_key_and_values_match(self):
        json_data_Q2 = Test_URL_Questionnaire_Submit_Wrong_Type.json()
        self.assertIn("questionnaireId", json_data_Q2)
        self.assertIsInstance(["questionnairId"], int)
        self.assertIn("userId", json_data_Q2)
        self.assertIsInstance(["userId"], str)
        for answer in json_data_Q2["answeredQuestions"]:
            self.assertIn("id", answer)
            self.assertIsInstance(answer["id"], int)
            self.assertIn("type", answer)
            self.assertIsInstance(answer["type"], str)
            self.assertIn("answer", answer)
            self.assertIsInstance(answer["answer"], int)

    def test_post_questionnaire_when_submit_correct_body_then_errorCode_106(self):
        retrieve_error = Test_URL_Questionnaire_Submit_Correct_Body.json()
        error_check = retrieve_error.get("errorCode")
        error_message = retrieve_error.get("developerMessage")
        self.assertEqual(error_check, 106)
        self.assertIsNotNone(error_message)

    def test_post_questionnaire_when_submit_correct_body_then_status_code_200(self):
        self.assertEqual(Test_URL_Questionnaire_Submit_Correct_Body.status_code, 200)
    
    def test_post_questionnaire_when_submit_correct_body_then_key_and_values_match(self):
        json_data_Q2 = Test_URL_Questionnaire_Submit_Correct_Body.json()
        self.assertIn("questionnaireId", json_data_Q2)
        self.assertIsInstance(["questionnairId"], int)
        self.assertIn("userId", json_data_Q2)
        self.assertIsInstance(["userId"], str)
        for answer in json_data_Q2["answeredQuestions"]:
            self.assertIn("id", answer)
            self.assertIsInstance(answer["id"], int)
            self.assertIn("type", answer)
            self.assertIsInstance(answer["type"], str)
            self.assertIn("answer", answer)
            self.assertIsInstance(answer["answer"], int)

# Test 3
## Looking for the correct return
            
test_full_questionnaire_time = {
    "questionnaireId": 1,
    "userId": "UserId",
    "answeredQuestions": [
        {
            "id": 0,
            "answer": 10,
            "type": "Unbreakable"
        },
        {
            "id": 1,
            "answer": 5,
            "type": "Rejected"
        },
        {
            "id": 2,
            "answer": 5,
            "type": "Rejected"
        },
        {
            "id": 3,
            "answer": 5,
            "type": "Conformer"
        },
        {
            "id": 4,
            "answer": 5,
            "type": "Inspector"
        },
        {
            "id": 5,
            "answer": 5,
            "type": "Rejected"
        },
        {
            "id": 6,
            "answer": 5,
            "type": "Doer"
        },
        {
            "id": 7,
            "answer": 5,
            "type": "Inspector"
        },
        {
            "id": 8,
            "answer": 10,
            "type": "Unbreakable"
        },
        {
            "id": 9,
            "answer": 5,
            "type": "Savior"
        },
        {
            "id": 10,
            "answer": 5,
            "type": "Dreamer"
        },
        {
            "id": 11,
            "answer": 5,
            "type": "Dreamer"
        },
        {
            "id": 12,
            "answer": 5,
            "type": "Conformer"
        },
        {
            "id": 13,
            "answer": 5,
            "type": "Doer"
        },
        {
            "id": 14,
            "answer": 5,
            "type": "Savior"
        },
        {
            "id": 15,
            "answer": 5,
            "type": "Pessimist"
        },
        {
            "id": 16,
            "answer": 5,
            "type": "Inspector"
        },
        {
            "id": 17,
            "answer": 5,
            "type": "Conformer"
        },
        {
            "id": 18,
            "answer": 5,
            "type": "Conformer"
        },
        {
            "id": 19,
            "answer": 5,
            "type": "Savior"
        },
        {
            "id": 20,
            "answer": 5,
            "type": "Dreamer"
        },
        {
            "id": 21,
            "answer": 5,
            "type": "Doer"
        },
        {
            "id": 22,
            "answer": 5,
            "type": "Savior"
        },
        {
            "id": 23,
            "answer": 5,
            "type": "Savior"
        },
        {
            "id": 24,
            "answer": 5,
            "type": "Pessimist"
        },
        {
            "id": 25,
            "answer": 5,
            "type": "Doer"
        },
        {
            "id": 26,
            "answer": 5,
            "type": "Pessimist"
        },
        {
            "id": 27,
            "answer": 5,
            "type": "Unbreakable"
        },
        {
            "id": 28,
            "answer": 5,
            "type": "Dreamer"
        },
        {
            "id": 29,
            "answer": 5,
            "type": "Inspector"
        },
        {
            "id": 30,
            "answer": 5,
            "type": "Doer"
        },
        {
            "id": 31,
            "answer": 5,
            "type": "Rejected"
        },
        {
            "id": 32,
            "answer": 5,
            "type": "Unbreakable"
        },
        {
            "id": 33,
            "answer": 5,
            "type": "Pessimist"
        },
        {
            "id": 34,
            "answer": 5,
            "type": "Conformer"
        },
        {
            "id": 35,
            "answer": 5,
            "type": "Inspector"
        },
        {
            "id": 36,
            "answer": 5,
            "type": "Unbreakable"
        },
        {
            "id": 37,
            "answer": 5,
            "type": "Dreamer"
        },
        {
            "id": 38,
            "answer": 5,
            "type": "Pessimist"
        },
        {
            "id": 39,
            "answer": 5,
            "type": "Rejected"
        }
    ]
}

Test_URL_Submit_Answer_With_All_Results = make_post_call("questionnaire/submit/answer", test_full_questionnaire_time)

class Tests_Submit_All_Answers_Body_Status_and_Inputs(unittest.TestCase):
    maxDiff = None
    
    def test_post_questionnaire_when_submit_all_answers_then_errorCode_105(self):
        retrieve_error = Test_URL_Submit_Answer_With_All_Results.json()
        error_check = retrieve_error.get("errorCode")
        error_message = retrieve_error.get("developerMessage")
        self.assertEqual(error_check, 105)
        self.assertIsNotNone(error_message)

    def test_post_questionnaire_when_submit_all_answers_then_status_code_200(self):
        self.assertEqual(Test_URL_Submit_Answer_With_All_Results.status_code, 200)
    
    def test_post_questionnaire_when_submit_all_answers_then_key_and_values_match(self):
        json_data_Q3 = Test_URL_Submit_Answer_With_All_Results.json()
        self.assertIn("results", json_data_Q3)
        self.assertEqual(len(json_data_Q3["results"]), 3)
        for results in json_data_Q3["results"]:
            self.assertIsInstance(results, dict)
            self.assertEqual(len(results), 1)
            key, value = next(iter(results.items()))
            self.assertIsInstance(key, str)
            self.assertIsInstance(value, float)

    def test_post_questionnaire_when_submit_all_answers_then_match_expected_resutls(self):
        expected_results = {
            "Unbreakable": 0.41,
            "Doer": 0.29,
            "Rejected": 0.29
            }
        json_data_Q3 = Test_URL_Submit_Answer_With_All_Results.json()
        self.assertIn("results", json_data_Q3)
        self.assertEqual(len(json_data_Q3["results"]),len(expected_results))
        for expected_key, expected_value in expected_results.items():
            found = False
            for results in json_data_Q3["results"]:
                if expected_key in results and results[expected_key] == expected_value:
                    found = True
                    break
        self.assertTrue(found, f"Expected result {expected_key}: {expected_value} not found")  


#Test 4
## Random url call
Test_URL_Questionnaire_Random_Text = make_get_call("questions/questionnairebkasdkasmd")

class Tests_Questionnair_Random_Text_Status(unittest.TestCase):
    maxDiff = None

    def test_get_questionnaire_when_questionnaire_random_text_then_status_code_200(self):
        self.assertEqual(Test_URL_Questionnaire_Random_Text.status_code, 200)
    
    def test_get_questionnaire_when_questionnaire_random_text_then_status_200(self):
        retrieve_status = Test_URL_Questionnaire_Random_Text.json()
        status_check = retrieve_status.get("status")
        self.assertEqual(status_check, 200)

if __name__ == '__main__':
    unittest.main(testRunner=unittest.TextTestRunner(verbosity=2, resultclass=ColorfulResult))



#### for later | run tests in different classes #### too messy
    ## Create a test suite with specific test classes
    # suite = unittest.TestSuite()
    # suite.addTest(unittest.makeSuite(TestSomeOtherFunctionality))

    # # Run the test suite
    # runner = unittest.TextTestRunner()
    # runner.run(suite)


# ### POST answer
# #### Status code: 200
# #### Body
# ```
# {
#     "results": [
#         {
#             String: Double // PersonalityType : Double
#         },
#         {
#             String: Double
#         },
#         {
#             String: Double
#         }
#     ]
# }
# ```