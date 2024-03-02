# Ale's transition to the coding realm

## RestApi specs

### GET baseUrl/questions/questionnaire1
#### Status code: 200
#### Body
```
{   
    "questions": [
        {
            "id": Int,
            "title": String,
            "type": String
        }
        ...
    ]
}
```

### POST baseUrl//questionnaire/submitanswer
### Request
#### Body
```
{
    "questionnaireId": Int,
    "userId": String,
    "answeredQuestions": [
        {
            "id": Int,
            "type": String,
            "answer": Int
        }
        ...
    ]
}
```

### POST answer
#### Status code: 200
#### Body
```
{
    "results": [
        {
            String: Double // PersonalityType : Double
        },
        {
            String: Double
        },
        {
            String: Double
        }
    ]
}
```


## Errors
#### GET error case
#### Status code: 200
#### Body
```
{   
    "type": String,
    "developerMessage": String,
    "errorCode": Int,
    "error": String,
    "status": Int
}
```