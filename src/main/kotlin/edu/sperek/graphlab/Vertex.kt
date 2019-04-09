package edu.sperek.graphlab

class Vertex(val position: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vertex

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position
    }

    override fun toString(): String {
        return "$position"
    }
}