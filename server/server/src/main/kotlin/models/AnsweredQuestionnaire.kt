package com.alessio.models

import kotlinx.serialization.Serializable

@Serializable
data class AnsweredQuestionnaire(
    val questionnaireId: Int,
    val userId: String,
    val answeredQuestions: List<AnsweredQuestion>
)