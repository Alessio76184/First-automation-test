package com.alessio.utils

import com.alessio.exceptions.ServerError
import com.alessio.models.AnsweredQuestionnaire
import com.alessio.plugins.questionnaireLoader

class DataValidators {

    companion object {
        fun validateQuestionnaireAnswers(data: AnsweredQuestionnaire) {
            val localQuestionnaire = questionnaireLoader.getQuestionnaire(data.questionnaireId)
            if (data.answeredQuestions.size != localQuestionnaire.questions.size) {
                throw ServerError.QuestionnaireAnswersIncomplete()
            }
        }

        fun validateQuestionnaireId(
            text: AnsweredQuestionnaire
        ) {
            if (!questionnaireLoader.doesQuestionnaireExist(text.questionnaireId)) {
                throw ServerError.QuestionnaireNonExistent()
            }
        }
    }
}