package com.alessio.plugins

import com.alessio.data.QuestionnaireLoader
import com.alessio.exceptions.ServerError
import com.alessio.models.AnsweredQuestionnaire
import com.alessio.utils.DataValidators.Companion.validateQuestionnaireAnswers
import com.alessio.utils.DataValidators.Companion.validateQuestionnaireId
import com.alessio.utils.ResultsCalculator
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

val questionnaireLoader = QuestionnaireLoader()

fun Application.configureRouting() {
    routing {
        get("/about") {
            call.respondText("App version: ${System.getProperty("version")}")
        }
        get("/questions/questionnaire{questionnaireNumber}") {
            try {
                val questionnaireNumber = call.parameters["questionnaireNumber"].validateQuestionnaireNumber()
                val questionnaireToReturn = questionnaireLoader.getQuestionnaire(questionnaireNumber)
                call.respond(questionnaireToReturn)
            } catch (e: ServerError) {
                call.respond(e)
            } catch (e: Exception) {
                call.respond(
                    ServerError.Generic(
                        error = e.message,
                        developerMessage = e.cause.toString()
                    )
                )
            }
        }
        post("/questionnaire/submit/answer") {
            try {
                val data = call.receive<AnsweredQuestionnaire>()
                validateQuestionnaireId(data)
                validateQuestionnaireAnswers(data)
                val computedResults = ResultsCalculator().computeResultsForAnswers(data.answeredQuestions)
                call.respond(computedResults)
            } catch (exception: BadRequestException) {
                call.respond(
                    ServerError.BadRequest(
                        error = exception.cause?.message
                    )
                )
            } catch (handledServerError: ServerError) {
                call.respond(handledServerError)
            }
        }
    }
}

@Throws(ServerError::class)
private fun String?.validateQuestionnaireNumber(): Int {
    if (this.isNullOrEmpty()) {
        throw ServerError.NoQuestionnaireProvided()
    }
    // add validation for int casting
    return this.toInt()
}
