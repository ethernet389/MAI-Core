package math.matrix_extensions

import Jama.Matrix
import kotlinx.serialization.Serializable


@JvmInline
@Serializable
value class KMatrix(@Serializable(with = MatrixSerializer::class) val value: Matrix)

//Properties Extensions
val Matrix.sumOfColumns: List<Double>
    get() {
        val columnSum = MutableList(columnDimension) {0.0}
        for (j in 0..<columnDimension) {
            var sum = 0.0
            for (i in 0..<rowDimension) {
                sum += this[i, j]
            }
            columnSum[j] = sum
        }
        return columnSum
    }
val Matrix.sumOfRows: List<Double>
    get() {
        val linesSum = MutableList(rowDimension) {0.0}
        for (j in 0..<rowDimension) {
            var sum = 0.0
            for (i in 0..<columnDimension) {
                sum += this[j, i]
            }
            linesSum[j] = sum
        }
        return linesSum
    }
val Matrix.relativeWeights: List<Double>
    get() {
        val ret = sumOfRows.toMutableList()
        for (i in ret.indices) ret[i] /= columnDimension.toDouble()
        return ret
    }

fun Matrix.normalize(): Matrix {
    val columnSum = sumOfColumns
    for (i in 0..<rowDimension) {
        for (j in 0..<columnDimension) {
            val newValue: Double = this[j, i] / columnSum[i]
            this[j, i] = newValue
        }
    }
    return this
}
val Matrix.normalized
    get() = Matrix(arrayCopy).normalize()

object MaiCoefficients{
    fun CI(matrix: KMatrix): Double = with(matrix.value) {
        val normalizedMatrixWeights =
            normalized.relativeWeights
        val weights = Matrix(normalizedMatrixWeights.size, 1)
        for (i in 0..<weights.rowDimension) weights[i, 0] = normalizedMatrixWeights[i]
        val resultMatrix = this * weights
        return (resultMatrix.sumOfColumns.first() - columnDimension) / (columnDimension - 1)
    }

    fun RI(matrix: KMatrix): Double = with(matrix.value) {
        (1.98 * (columnDimension - 2)) / columnDimension
    }

    fun CR(matrix: KMatrix): Double = CI(matrix) / RI(matrix)
}