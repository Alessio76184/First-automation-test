package com.alessio.models

import kotlinx.serialization.Serializable

@Serializable
enum class PersonalityType {
    Conformer,
    Inspector,
    Unbreakable,
    Dreamer,
    Pessimist,
    Rejected,
    Doer,
    Savior,
    Unknown,
}