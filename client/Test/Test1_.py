import requests
## Creating a home link which will stay constant for testing
baseUrl = "http://localhost:8080/"
## Creating the variable to include the link we want to test 
## !! could we automate this later? do I need to?
URL = baseUrl + "questions/questionnaire1"

response = requests.get(URL)
## this collects data from json to python
data = response.json()

print(URL)
print('Status code:', response.status_code)

# Establishing the variable for erroCode and 
if 'errorCode' in data:
    error_code = data['errorCode']
    error_message = data.get('error', 'No error message provided')
    print(f'Error Message: {error_message}')
    
# Checking if the status code matches 200 and that it matches the errorCode
try:
    assert response.status_code == 200 and error_code == response.status_code, f"Status code: {response.status_code}, Error code: {error_code}"
    print("Assertion passed.")
except AssertionError as e:
    print("Assertion fialed: ", e)
    
# Checking if key's in 'Question' body esxist
if 'questions' in data:
    for question in data['questions']:
        assert all(key in question for key in ('id', 'title', 'type')), f"Missing keys in question: {question}"
        for key in ('id', 'title', 'type'):
            if key == 'id':
                assert isinstance(question[key], int), f"Incorrect type for '{key}' in question: {question}"
            else:
                assert isinstance(question[key], str), f"Incorrect type for '{key}' in question: {question}"
    print("All questions have the required keys and types.")
else:
    print("No 'questions' key found in the response.")

### GET baseUrl/questions/questionnaire1
#### Status code: 200
#### Body
##```
##{   
##    "questions": [
##        {
##            "id": Int,
##            "title": String,
##            "type": String
##        }
##        ...
##    ]
##}
##```
