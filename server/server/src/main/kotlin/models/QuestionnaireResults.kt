package com.alessio.models

import kotlinx.serialization.Serializable

@Serializable
data class QuestionnaireResults(
    val results: List<Map<PersonalityType, Double>>
)
