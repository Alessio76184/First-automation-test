package com.alessio.models

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: Int,
    val title: String,
    var answer: Int = 0,
    val type: PersonalityType,
)