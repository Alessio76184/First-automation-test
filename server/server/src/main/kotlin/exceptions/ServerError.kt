package com.alessio.exceptions

import kotlinx.serialization.Serializable

@Serializable
sealed class ServerError : RuntimeException() {

    abstract val status: Int
    abstract val errorCode: Int?
    abstract val error: String?
    abstract val developerMessage: String?

    // questions/questionnaire{questionnaireNumber} errors
    /**
     * Returned when a GET call to [questions] does not include an identifier Int.
     */
    @Serializable
    class NoQuestionnaireProvided(
        override val developerMessage: String? = "Consider including an Integer in the parameters",
        override val errorCode: Int? = 101,
        override val error: String? = "No questionnaire identifier provided",
        override val status: Int = 400
    ) : ServerError()

    /**
     * Returned when a GET call to [questions] is requested for a questionnaire that does not exist.
     */
    @Serializable
    class QuestionnaireNotAvailable(
        override val developerMessage: String? = "Consider using a different Integer",
        override val errorCode: Int? = 102,
        override var error: String? = "Provided questionnaire identifier does not exist",
        override var status: Int = 400
    ) : ServerError()

    @Serializable
    class Generic(
        override var developerMessage: String? = null,
        override var errorCode: Int? = null,
        override val error: String? = null,
        override val status: Int = 400
    ) : ServerError()

    @Serializable
    class BadRequest(
        override var developerMessage: String? = "Your request was shit, go fly a kite",
        override var errorCode: Int? = 104,
        override var error: String? = null,
        override var status: Int = 400
    ) : ServerError()

    @Serializable
    class QuestionnaireNonExistent(
        override var developerMessage: String? = "You tried to send answers to a non-existent questionnaire",
        override var errorCode: Int? = 105,
        override var error: String? = null,
        override var status: Int = 400
    ) : ServerError()

    @Serializable
    class QuestionnaireAnswersIncomplete(
        override var developerMessage: String? = "Incomplete questionnaire answers",
        override var errorCode: Int? = 106,
        override var error: String? = null,
        override var status: Int = 400
    ) : ServerError()
}