package math

import math.mai.InputParameters
import math.mai.MAI
import math.matrix_extensions.MaiCoefficients
import java.io.File
import java.util.*

val input = Scanner(System.`in`)

fun main() {
    println("Входной файл:")
    val data = File("data_sets/${input.nextLine()}")
    val scan = Scanner(data)

    val inputPars = InputParameters.decodeFromString(scan.nextLine())

    val buffer = MAI(inputPars)
    println("Relative Weights")
    buffer.relativeWeights.forEach {
        println(it.toString())
    }
    println("Final Weights")
    println(buffer.finalRelativeWeights.toString())
    println()

    with(inputPars.criteriaMatrix) {
        val str = """
            | Criteria matrix
            | CI: ${MaiCoefficients.CI(this)}
            | RI: ${MaiCoefficients.RI(this)}
            | CR: ${MaiCoefficients.CR(this)}
        """.trimIndent()
        println("$str \n")
    }

    inputPars.candidatesMatrix.forEachIndexed { i, inputMatrix ->
        val str = """
            | Alternatives matrix #${i+1}
            | CI: ${MaiCoefficients.CI(inputMatrix)}
            | RI: ${MaiCoefficients.RI(inputMatrix)}
            | CR: ${MaiCoefficients.CR(inputMatrix)}
        """.trimIndent()
        println("$str \n")
    }
}