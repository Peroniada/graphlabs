package edu.sperek.graphlab

class GraphModel(val order: Int, val size: Int, private val edges: List<Edge>) {

    val edgesList: List<String>
        get() = edges.map { edge -> edge.toString() }

    val verticesList: List<Vertex>
        get() = determineVertices()

    val adjacencyMatrix: Array<Array<Int>>
        get() = fillAdjacencyOf(emptyMatrix(order, order))

    val incidenceMatrix: Array<Array<Int>>
        get() = fillIncidenceOf(emptyMatrix(order, size))

    val verticesDegrees: List<Pair<Vertex, Int>>
        get() = determineVerticesDegrees()

    val degreesSequence: List<Int>
        get() = determineDegreesSequence()

    val isSimple: Boolean
        get() = edges.none { edge -> edge.start == edge.end }

    val fulfillingEdges: List<Edge>
        get() = determineFulfillingEdges()

    val getNeighbors: Map<Vertex, Set<Vertex>>
        get() = determineNeighbors()

    private fun determineNeighbors(): Map<Vertex, Set<Vertex>> {
        val neighbors: HashMap<Vertex, Set<Vertex>> = hashMapOf()
        edges.forEach { edge ->
            run {
                addStartVertex(edge, neighbors)
                addEndVertex(edge, neighbors)
            }
        }
        return neighbors
    }

    private fun addStartVertex(edge: Edge, map: HashMap<Vertex, Set<Vertex>>) {
        if (map.containsKey(edge.start)) {
            val x = map[edge.start]!!.toMutableSet()
            x += edge.end
            map[edge.start] = x
        } else {
            map[edge.start] = mutableSetOf(edge.end)
        }
    }

    private fun addEndVertex(edge: Edge, map: HashMap<Vertex, Set<Vertex>>) {
        if (map.containsKey(edge.end)) {
            val x = map[edge.end]!!.toMutableSet()
            x += edge.start
            map[edge.end] = x
        } else {
            map[edge.end] = mutableSetOf(edge.start)
        }
    }

    private fun fillAdjacencyOf(matrix: Array<Array<Int>>): Array<Array<Int>> {
        edges.forEach { edge ->
            run {
                matrix[edge.startPosition - 1][edge.endPosition - 1] += 1
                matrix[edge.endPosition - 1][edge.startPosition - 1] += 1
            }
        }
        return matrix
    }

    private fun fillIncidenceOf(matrix: Array<Array<Int>>): Array<Array<Int>> {
        edges.forEachIndexed { index, edge ->
            run {
                verticesList.forEach { vertex ->
                    matrix[vertex.position - 1][index] = if (edge.contains(vertex)) 1 else 0
                }
            }
        }
        return matrix
    }

    private fun determineVertices(): List<Vertex> {
        val vertices = mutableListOf<Vertex>()
        edges.forEach { edge -> vertices.add(edge.start); vertices.add(edge.end) }
        return vertices.distinct().sortedBy { vertex -> vertex.position }
    }

    private fun determineVerticesDegrees(): List<Pair<Vertex, Int>> {
        return adjacencyMatrix.mapIndexed { index, row -> Pair(Vertex(index + 1), row.sum()) }
    }

    private fun determineDegreesSequence(): List<Int> {
        return adjacencyMatrix.map { row -> row.sum() }
                .sorted()
    }

    private fun determineFulfillingEdges(): MutableList<Edge> {
        val adjMatrix = adjacencyMatrix
        val fulfillingEdges = mutableListOf<Edge>()
        for (row in 1..order) {
            for (col in (row + 1)..order) {
                val matrixCellValue = adjMatrix[row - 1][col - 1]
                if (matrixCellValue == 0) fulfillingEdges.add(Edge(row, col))
            }
        }
        return fulfillingEdges
    }

    private fun emptyMatrix(rows: Int, columns: Int): Array<Array<Int>> {
        val arr = IntArray(columns) { 0 }
        return Array(rows) { arr.toTypedArray() }
    }
}
