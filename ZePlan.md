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

### POST baseUrl/question/answer
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

### Answer
#### Status code: 200
#### Body
```
{
    "status": Int
    "errorCode": Int?,
    "error": String?,
}
```