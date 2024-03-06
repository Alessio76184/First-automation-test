import requests
baseUrl = "http://localhost:8080/"
 
# handles the calls 
def make_get_call(URL):
    response = requests.get(baseUrl+URL)
    ## this collects data from json to python
    return response.json()

def make_post_call(URL, body):
    pass

# 
def QuestionnaireIdCount(questions):
    ## len counts any time ques
    id_count = 0
    for question in questions.get('questions', []):
        if question.get('id') is not None:
            id_count += 1
    return id_count


