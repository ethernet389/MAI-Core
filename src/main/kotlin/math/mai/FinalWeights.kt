package math.mai

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import math.matrix_extensions.KMatrix

@Serializable
data class FinalWeights(
    val relativeWeights: List<List<Double>>, //Row - criterion, column - candidate
    val finalRelativeWeights: List<Double> //Row - final, column - candidate
) {
    fun encodeToString() = Json.encodeToString(this)

    companion object {
        fun decodeFromString(string: String) = Json.decodeFromString<FinalWeights>(string)
    }
}

@Serializable
data class InputParameters(
    val criteriaMatrix: KMatrix,
    val candidatesMatrix: List<KMatrix>
) {
    fun encodeToString() = Json.encodeToString(this)

    companion object {
        fun decodeFromString(string: String): InputParameters = Json.decodeFromString(string)
    }
}