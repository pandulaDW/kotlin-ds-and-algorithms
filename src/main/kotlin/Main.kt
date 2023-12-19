import datastructures.Heap
import datastructures.SortOrder

fun main() {
    val h = Heap<Int>(SortOrder.Max)

    val l = arrayListOf(90, 58, 72, 61, 55, 100, 101, -10, 33)
    l.forEach { h.insert(it) }

//    h.removeRoot()
    println(h)
}