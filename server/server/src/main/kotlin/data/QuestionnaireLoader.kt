package com.alessio.data

import com.alessio.exceptions.ServerError
import com.alessio.models.Questionnaire
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException

class QuestionnaireLoader {


    fun getQuestionnaire(questionnaireIdentifier: Int): Questionnaire {
        try {
            val data = File("$LOCATION_OF_QUESTIONNAIRES$questionnaireIdentifier.json").readText()
            val questionnaireToReturn = Json.decodeFromString<Questionnaire>(data)
            return questionnaireToReturn
        } catch (e: FileNotFoundException) {
            throw ServerError.QuestionnaireNotAvailable()
        }
    }

    fun doesQuestionnaireExist(questionnaireIdentifier: Int): Boolean {
        return try {
            File("$LOCATION_OF_QUESTIONNAIRES$questionnaireIdentifier.json").readText().isNotEmpty()
        } catch (e: FileNotFoundException) {
            false
        }
    }

    companion object {
        private const val LOCATION_OF_QUESTIONNAIRES = "src/main/kotlin/resources/questionnaire"
    }
}