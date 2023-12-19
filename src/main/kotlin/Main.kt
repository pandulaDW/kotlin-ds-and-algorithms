import datastructures.Heap
import datastructures.SortOrder

fun main() {
    val h = Heap<Char>(SortOrder.Max)

    val l = arrayListOf('a', 'b', 'c', 'z', 'x')
    l.forEach { h.insert(it) }

//    h.removeRoot()
    println(h)
}