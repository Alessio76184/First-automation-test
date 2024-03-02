package com.alessio.models

import kotlinx.serialization.Serializable

@Serializable
data class AnsweredQuestion(
    val id: Int,
    val type: PersonalityType,
    val answer: Int,
)
