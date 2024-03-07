import requests
baseUrl = "http://localhost:8080/"
 
# handles the calls 
def make_get_call(URL):
    response = requests.get(baseUrl + URL)
    return response

def make_post_call(URL, body):
    response = requests.post(baseUrl + URL, json=body)
    return response  # Return the response object instead of JSON dat
    
# Counting the number of questions within the questionnaire
def QuestionnaireIdCount(questions):
    id_count = 0
    # Convert the response to JSON and then access the 'questions' attribute
    json_data = questions.json()
    for question in json_data.get('questions', []):
        if question.get('id') is not None:
            id_count += 1
    return id_count

