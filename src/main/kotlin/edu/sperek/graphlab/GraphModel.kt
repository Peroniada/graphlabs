package edu.sperek.graphlab

class GraphModel(val numberOfVertices: Int, val numberOfEdges: Int, val edges: List<Pair<Int, Int>>) {

    val edgesList: List<String>
        get() = edges.map { edge -> "${edge.first}-${edge.second}" }

//    val adjacencyMatrix: Array<Array<Int>>
//        get() {
//
//        var matrix = arrayOf<Array<Int>>()
//            for(i in 0..numberOfVertices) {
//                var array = arrayOf<Int>()
//                array+=0
//            }
//        }

    val vertsList : List<Int>
        get() {
            val verts = mutableListOf<Int>()
            edges.forEach {pair -> verts.addAll(pair.toList())}
            return verts.distinct().sorted()
        }
}