package com.alessio.utils

import com.alessio.models.AnsweredQuestion
import com.alessio.models.PersonalityType
import com.alessio.models.QuestionnaireResults
import kotlin.math.roundToInt

class ResultsCalculator {

    fun computeResultsForAnswers(data: List<AnsweredQuestion>): QuestionnaireResults {
        val personalityTypes = setOf(
            PersonalityType.Doer,
            PersonalityType.Unbreakable,
            PersonalityType.Rejected,
            PersonalityType.Savior,
            PersonalityType.Inspector,
            PersonalityType.Pessimist,
            PersonalityType.Conformer,
            PersonalityType.Dreamer
        )
        val result = personalityTypes.map {
            getScoreForPersonalityType(data, it)
        }.sortedByDescending {
            it.values.maxByOrNull { score -> score }
        }.subList(0, 3)
            .calculatePercentage()

        return QuestionnaireResults(result)
    }

    private fun getScoreForPersonalityType(
        answers: List<AnsweredQuestion>,
        personalityType: PersonalityType
    ): MutableMap<PersonalityType, Double> =
        mutableMapOf(Pair(personalityType, answers.filter {
            it.type == personalityType
        }.sumOf {
            it.answer.toDouble()
        }))

    private fun List<Map<PersonalityType, Double>>.calculatePercentage(): List<Map<PersonalityType, Double>> {
        val totalScore = getTotalScore(this)
        val processedResultsInPercentages = mutableListOf<Map<PersonalityType, Double>>()
        this.forEach { entry ->
            entry.mapValues { value ->
                val percentage = value.value / totalScore
                val trimmedPercentage = (percentage * 100.0).roundToInt() / 100.0
                processedResultsInPercentages.add(
                    mapOf(Pair(entry.keys.first(), trimmedPercentage))
                )
            }
        }
        return processedResultsInPercentages
    }

    private fun getTotalScore(result: List<Map<PersonalityType, Double>>): Double {
        var totalValue = 0.0
        result.forEach {
            totalValue += it.values.first()
        }
        return totalValue
    }
}