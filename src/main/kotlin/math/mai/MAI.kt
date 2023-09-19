package math.mai

import math.matrix_extensions.normalized
import math.matrix_extensions.relativeWeights

fun MAI(inputParameters: InputParameters): FinalWeights {
    val criteriaMatrix = inputParameters.criteriaMatrix.value.normalized
    val candidatesMatrix = inputParameters.candidatesMatrix.map { it.value.normalized }
    val numAlt = candidatesMatrix.first().columnDimension

    val listOfWeights = mutableListOf<MutableList<Double>>()
    for (e in candidatesMatrix) listOfWeights.add(e.relativeWeights.toMutableList())

    val perWeights = criteriaMatrix.relativeWeights
    for (i in perWeights.indices) {
        for (j in 0..<numAlt) listOfWeights[i][j] *= perWeights[i]
    }

    val result = MutableList(numAlt) { 0.0 }
    for (i in perWeights.indices) {
        for (j in 0..<numAlt) result[j] += listOfWeights[i][j]
    }

    return FinalWeights(relativeWeights = listOfWeights, finalRelativeWeights = result)
}