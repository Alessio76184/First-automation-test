package com.alessio.exceptions

import kotlinx.serialization.Serializable

@Serializable
sealed class ServerError(
    val status: Int = 400,
    val errorCode: Int? = null,
    val error: String? = null,
    val developerMessage: String? = null
) : RuntimeException() {

    // questions/questionnaire{questionnaireNumber} errors
    /**
     * Returned when a GET call to [questions] does not include an identifier Int.
     */
    @Serializable
    class NoQuestionnaireProvided : ServerError(
        error = "No questionnaire identifier provided",
        errorCode = 101,
        developerMessage = "Consider including an Integer in the parameters"
    )

    /**
     * Returned when a GET call to [questions] is requested for a questionnaire that does not exist.
     */
    @Serializable
    class QuestionnaireNotAvailable : ServerError(
        error = "Provided questionnaire identifier does not exist",
        errorCode = 102,
        developerMessage = "Consider using a different Integer"
    )
}