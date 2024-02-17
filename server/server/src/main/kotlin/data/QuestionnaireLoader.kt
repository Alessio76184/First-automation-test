package com.alessio.data

import com.alessio.exceptions.ServerError
import com.alessio.models.Questionnaire
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException
import kotlin.io.path.Path
import kotlin.io.path.listDirectoryEntries

class QuestionnaireLoader {

    private val availableQuestionnaires = Path("assets")

    fun getQuestionnaire(questionnaireIdentifier: Int): Questionnaire {
        try {
            val data = File("src/main/kotlin/resources/questionnaire$questionnaireIdentifier.json").readText()
            val questionnaireToReturn = Json.decodeFromString<Questionnaire>(data)
            return questionnaireToReturn
        } catch (e: FileNotFoundException) {
            throw ServerError.QuestionnaireNotAvailable()
        }
    }
}