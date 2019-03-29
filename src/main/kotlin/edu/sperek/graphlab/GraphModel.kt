package edu.sperek.graphlab

class GraphModel(val order: Int, val size: Int, private val edges: List<Pair<Int, Int>>) {

    val edgesList: List<String>
        get() = edges.map { edge -> "${edge.first}-${edge.second}" }

    val verticesList: List<Int>
        get() = determineVertices()

    val adjacencyMatrix: Array<Array<Int>>
        get() = fillAdjacencyOf(emptyMatrix(order, order))

    val incidenceMatrix: Array<Array<Int>>
        get() = fillIncidenceOf(emptyMatrix(order, size))

    val verticesDegrees: List<Pair<Int, Int>>
        get() = determineVerticesDegrees()

    val degreesSequence: List<Int>
        get() = determineDegreesSequence()

    val isSimple: Boolean
        get() = edges.none { edge -> edge.first == edge.second }

    val fulfillingEdges: List<Pair<Int, Int>>
        get() = determineFulfillingEdges()

    private fun fillAdjacencyOf(matrix: Array<Array<Int>>): Array<Array<Int>> {
        edges.forEach { edge ->
            run {
                matrix[edge.first - 1][edge.second - 1] += 1
                matrix[edge.second - 1][edge.first - 1] += 1
            }
        }
        return matrix
    }

    private fun fillIncidenceOf(matrix: Array<Array<Int>>): Array<Array<Int>> {
        edges.forEachIndexed { index, edge ->
            run {
                verticesList.forEach { vertex ->
                    matrix[vertex - 1][index] = if (edge.toList().contains(vertex)) 1 else 0
                }
            }
        }
        return matrix
    }

    private fun determineVertices(): List<Int> {
        val vertices = mutableListOf<Int>()
        edges.forEach { pair -> vertices.addAll(pair.toList()) }
        return vertices.distinct().sorted()
    }

    private fun determineVerticesDegrees(): List<Pair<Int, Int>> {
        return adjacencyMatrix.mapIndexed { index, row -> Pair(index + 1, row.sum()) }
    }

    private fun determineDegreesSequence(): List<Int> {
        return adjacencyMatrix.map { row -> row.sum() }
                .sorted()
    }

    private fun determineFulfillingEdges(): MutableList<Pair<Int, Int>> {
        val adjMatrix = adjacencyMatrix
        val fulfillingEdges = mutableListOf<Pair<Int, Int>>()
        for (row in 1..order) {
            for (col in (row + 1)..order) {
                val matrixCellValue = adjMatrix[row - 1][col - 1]
                if (matrixCellValue == 0) fulfillingEdges.add(Pair(row, col))
            }
        }
        return fulfillingEdges
    }

    private fun emptyMatrix(rows: Int, columns: Int): Array<Array<Int>> {
        val arr = IntArray(columns) { 0 }
        return Array(rows) { arr.toTypedArray() }
    }
}
