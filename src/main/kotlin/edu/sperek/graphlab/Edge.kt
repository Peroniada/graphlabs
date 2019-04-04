package edu.sperek.graphlab

class Edge(val start: Vertex, val end: Vertex) {

    val startPosition: Int
        get() = start.position

    val endPosition: Int
        get() = end.position

    constructor(start: Int, end:Int): this(Vertex(start), Vertex(end))

    fun contains(vertex: Vertex):Boolean {
       return start == vertex || end == vertex
    }

    override fun toString(): String {
        return "$start-$end"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Edge

        if (start != other.start) return false
        if (end != other.end) return false

        return true
    }

    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + end.hashCode()
        return result
    }


}