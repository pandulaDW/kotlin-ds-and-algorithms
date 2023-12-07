package datastructures

import kotlin.collections.HashMap

class Graph(private val isBiDirectional: Boolean = true) {
    private val adjacencyList = HashMap<String, ArrayList<String>>()

    fun addVertex(vertex: String): Boolean {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList[vertex] = arrayListOf();
            return true
        }
        return false
    }

    fun addEdge(vertex1: String, vertex2: String) {
        addVertex(vertex1)
        addVertex(vertex2)

        adjacencyList[vertex1]?.add(vertex2)
        if (isBiDirectional) adjacencyList[vertex2]?.add(vertex1)
    }

    override fun toString(): String {
        return adjacencyList.toString()
    }
}