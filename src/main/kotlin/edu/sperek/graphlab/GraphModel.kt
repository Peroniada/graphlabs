package edu.sperek.graphlab

class GraphModel(val numberOfVertices: Int, val numberOfEdges: Int, private val edges: List<Pair<Int, Int>>) {

    val edgesList: List<String>
        get() = edges.map { edge -> "${edge.first}-${edge.second}" }

    val verticesList: List<Int>
        get() = predicateVertices()

    val adjacencyMatrix: Array<Array<Int>>
        get() = fillAdjacencyOf(emptyMatrix(numberOfVertices, numberOfVertices))

    val incidenceMatrix: Array<Array<Int>>
        get() = fillIncidenceOf(emptyMatrix(numberOfVertices, numberOfEdges))

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

    private fun predicateVertices(): List<Int> {
        val vertices = mutableListOf<Int>()
        edges.forEach { pair -> vertices.addAll(pair.toList()) }
        return vertices.distinct().sorted()
    }

    private fun emptyMatrix(rows: Int, columns: Int): Array<Array<Int>> {
        val arr = IntArray(columns) { 0 }
        return Array(rows) { arr.toTypedArray() }
    }
}
