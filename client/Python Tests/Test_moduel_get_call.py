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
## Checking for the number of questions in the questionnair, checking the status & status code, checking the question structures match

TestQuestionnaire = make_get_call("questions/questionnaire1")

class Questionnaire1Tests(unittest.TestCase):
    maxDiff = None

    def test_get_answer_number_of_questions(self):
        id_count = QuestionnaireIdCount(TestQuestionnaire)
        self.assertEqual(id_count, 40)

    def test_get_answer_status_code(self):
        self.assertEqual(TestQuestionnaire.status_code, 200)
    
    def test_get_answer_status(self):
        retrieve_status = TestQuestionnaire.json()
        status_check = retrieve_status.get("status")
        self.assertEqual(status_check, 200)

    def test_questionare_body_structure(self):
        json_data = TestQuestionnaire.json()
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

TestQuestionnaire2 = make_post_call("questionnaire/submit/answer", test_entry_question_50)
TestQuestionnaire2_Type = make_post_call("questionnaire/submit/answer", test_entry_type_change)
TestQuestionnaire2_Expected = make_post_call("questionnaire/submit/answer", test_entry_expected)

class TestSubmitAnswer(unittest.TestCase):
    maxDiff = None
    
    def test_question_50(self):
        retrieve_error = TestQuestionnaire2.json()
        error_check = retrieve_error.get("errorCode")
        error_message = retrieve_error.get("developerMessage")
        self.assertEqual(error_check, 105)
        self.assertIsNotNone(error_message)

    def test_questionnaire_entry(self):
        self.assertEqual(TestQuestionnaire2.status_code, 200)
    
    def test_question_answer_50_structure(self):
        json_data_Q2 = TestQuestionnaire2.json()
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

    def test_question_type(self):
        retrieve_error = TestQuestionnaire2_Type.json()
        error_check = retrieve_error.get("errorCode")
        error_message = retrieve_error.get("developerMessage")
        self.assertEqual(error_check, 104)
        self.assertIsNotNone(error_message)

    def test_questionnaire_entry(self):
        self.assertEqual(TestQuestionnaire2_Type.status_code, 200)
    
    def test_question_answer_type_change_structure(self):
        json_data_Q2 = TestQuestionnaire2_Type.json()
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

    def test_question_expected(self):
        retrieve_error = TestQuestionnaire2_Expected.json()
        error_check = retrieve_error.get("errorCode")
        error_message = retrieve_error.get("developerMessage")
        self.assertEqual(error_check, 106)
        self.assertIsNotNone(error_message)

    def test_questionnaire_entry_expected(self):
        self.assertEqual(TestQuestionnaire2_Expected.status_code, 200)
    
    def test_question_answer_expected_structure(self):
        json_data_Q2 = TestQuestionnaire2_Expected.json()
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
## Looking for the correct retur
            
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

TestQuestionnaire3 = make_post_call("questionnaire/submit/answer", test_full_questionnaire_time)

class TestResultQuestionnaire(unittest.TestCase):
    maxDiff = None
    
    def test_full_questionnaire_error(self):
        retrieve_error = TestQuestionnaire3.json()
        error_check = retrieve_error.get("errorCode")
        error_message = retrieve_error.get("developerMessage")
        self.assertEqual(error_check, 105)
        self.assertIsNotNone(error_message)

    def test_full_questionnaire_status_check(self):
        self.assertEqual(TestQuestionnaire3.status_code, 200)
    
    def test_question_answer_results_structure(self):
        json_data_Q3 = TestQuestionnaire3.json()
        self.assertIn("results", json_data_Q3)
        self.assertEqual(len(json_data_Q3["results"]), 3)
        for results in json_data_Q3["results"]:
            self.assertIsInstance(results, dict)
            self.assertEqual(len(results), 1)
            key, value = next(iter(results.items()))
            self.assertIsInstance(key, str)
            self.assertIsInstance(value, float)

    def test_question_expected_results(self):
        expected_results = {
            "Unbreakable": 0.41,
            "Doer": 0.29,
            "Rejected": 0.29
            }
        json_data_Q3 = TestQuestionnaire3.json()
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
TestQuestionnaire4 = make_get_call("questions/questionnairebkasdkasmd")

class QuestionnaireRandomTests(unittest.TestCase):
    maxDiff = None

    def test_get_ramdom_url_status_code(self):
        self.assertEqual(TestQuestionnaire4.status_code, 200)
    
    def test_get_ramdom_url_status(self):
        retrieve_status = TestQuestionnaire4.json()
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