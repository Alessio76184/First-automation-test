import unittest
from connection_manager import make_get_call, QuestionnaireIdCount, make_post_call


#Test 1
## Checking for the number of questions in the questionnair, checking the status & status code, checking the question structures match

TestQuestionnaire = make_get_call("questions/questionnaire1")

class Questionnaire1Tests(unittest.TestCase):
    maxDiff = None

    def test_number_of_questions(self):
        id_count = QuestionnaireIdCount(TestQuestionnaire)
        self.assertEqual(id_count, 40)

    def test_status_code(self):
        self.assertEqual(TestQuestionnaire.status_code, 200)
    
    def test_status(self):
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
test_entry = {
    "questionnaireId": 32,
    "userId": "theman234",
    "answeredQuestions": [
        {"id": 0, "type": "I think multiple choice", "answer": 3},
    ]
}

TestQuestionnaire2 = make_post_call("questionnaire/submit/answer", test_entry)

class TestSubmitAnswer(unittest.TestCase):
    maxDiff = None

    def test_questionnaire_entry(self):
        self.assertEqual(TestQuestionnaire2.status_code, 200)

if __name__ == '__main__':
    unittest.main()

#### for later | run tests in different classes #### too messy
    ## Create a test suite with specific test classes
    # suite = unittest.TestSuite()
    # suite.addTest(unittest.makeSuite(TestSomeOtherFunctionality))

    # # Run the test suite
    # runner = unittest.TextTestRunner()
    # runner.run(suite)


# ### POST baseUrl//questionnaire/submitanswer
# ### Request
# #### Body
# ```
# {
#     "questionnaireId": Int,
#     "userId": String,
#     "answeredQuestions": [
#         {
#             "id": Int,
#             "type": String,
#             "answer": Int
#         }
#         ...
#     ]
# }
# ```

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


# ## Errors
# #### GET error case
# #### Status code: 200
# #### Body
# ```
# {   
#     "type": String,
#     "developerMessage": String,
#     "errorCode": Int,
#     "error": String,
#     "status": Int
# }
# ```