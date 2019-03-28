package edu.sperek.graphlab

class GraphModel(val numberOfVertices: Int, val numberOfEdges: Int, private val edges: List<Pair<Int, Int>>) {

    val edgesList: List<String>
        get() = edges.map { edge -> "${edge.first}-${edge.second}" }

    val adjacencyMatrix: Array<Array<Int>>
        get() {
            return fillAdjacencyOf(emptyMatrix())
        }

    val vertsList : List<Int>
        get() {
            val verts = mutableListOf<Int>()
            edges.forEach {pair -> verts.addAll(pair.toList())}
            return verts.distinct().sorted()
        }

    private fun fillAdjacencyOf(matrix: Array<Array<Int>>): Array<Array<Int>> {
        edges.forEach { edge ->
            run {
                matrix[edge.first - 1][edge.second - 1] += 1
                matrix[edge.second - 1][edge.first - 1] += 1
            }
        }
        return matrix
    }

    private fun emptyMatrix(): Array<Array<Int>> {
        val arr = IntArray(numberOfVertices) { 0 }
        return Array(numberOfVertices) {arr.toTypedArray()}
    }
}
