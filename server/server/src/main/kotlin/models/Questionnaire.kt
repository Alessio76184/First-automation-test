package com.alessio.models

import kotlinx.serialization.Serializable

@Serializable
data class Questionnaire(
    val questions: List<Question>
)
