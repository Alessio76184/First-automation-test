package models

data class Answers(
    val questionnaireId : Int,
    val userId : String,
    val answeredQuestions : List<AnsweredQuestion>
)
