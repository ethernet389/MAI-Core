package math

import math.mai.InputParameters
import math.mai.MAI
import math.matrix_extensions.MaiCoefficients
import java.io.File
import java.util.*

val input = Scanner(System.`in`)

fun main() {
    println("Входной файл")
    val data = File("data_sets/${input.nextLine()}")
    val scan = Scanner(data)

    val inputPars = InputParameters.decodeFromString(scan.nextLine())

    val buffer = MAI(inputPars)
    println(buffer.encodeToString())

    inputPars.candidatesMatrix.forEachIndexed { i, inputMatrix ->
        val str = """
            | Alt matrix #${i+1}
            | CI: ${MaiCoefficients.CI(inputMatrix)}
            | RI: ${MaiCoefficients.RI(inputMatrix)}
            | CR: ${MaiCoefficients.CR(inputMatrix)}
        """.trimIndent()
        println("$str \n")
    }
}