package com.alessio.plugins

import com.alessio.data.QuestionnaireLoader
import com.alessio.exceptions.ServerError
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

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
                call.respond(ServerError.Generic(
                    error = e.message,
                    developerMessage = e.cause.toString()
                ))
            }

        }
        post("questionnaire/submit/answer") {
            //the body
            //{
            //    "questionnaireId": Int,
            //    "userId": String,
            //    "answeredQuestions": [
            //        {
            //            "id": Int,
            //            "type": String,
            //            "answer": Int
            //        }
            //        ...
            //    ]
            //}
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
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
