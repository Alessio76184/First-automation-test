import unittest
from connection_manager import make_get_call, QuestionnaireIdCount

TestQuestionnaire = make_get_call("questions/questionnaire1")

class QuestionnaireTests(unittest.TestCase):
    maxDiff = None

    def test_number_of_questions(self):
        id_count = QuestionnaireIdCount(TestQuestionnaire)
        print("ID Count:", id_count) 
        self.assertEqual(id_count, 40)

if __name__ == '__main__':
    unittest.main()

#### for later | run tests in different classes ####
    # # Create a test suite with specific test classes
    # suite = unittest.TestSuite()
    # suite.addTest(unittest.makeSuite(TestSomeOtherFunctionality))

    # # Run the test suite
    # runner = unittest.TextTestRunner()
    # runner.run(suite)

### GET baseUrl/questions/questionnaire1
#### Status code: 200
#### Body
# ```
# {   
#     "questions": [
#         {
#             "id": Int,
#             "title": String,
#             "type": String
#         }
#         ...
#     ]
# }
# ```