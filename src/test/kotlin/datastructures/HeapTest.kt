package datastructures

import kotlin.test.*

class HeapTest {
    @Test
    fun insert() {
        val h = createHeap(99, 58, 72, 61, 55, 100)
        val expected = arrayListOf(null, 100, 61, 99, 58, 55, 72)
        assertContentEquals(expected, h.heapArray())
    }

    @Test
    fun remove() {
        // remove the root element
        val h = createHeap(95, 75, 55, 50, 80, 60, 65)
        h.remove(95)
        assertContentEquals(arrayListOf(null, 80, 75, 65, 50, 60, 55), h.heapArray())

        // remove a middle element
        h.remove(65)
        assertContentEquals(arrayListOf(null, 80, 75, 55, 50, 60), h.heapArray())

        // remove a leaf element
        h.remove(50)
        assertContentEquals(arrayListOf(null, 80, 75, 55, 60), h.heapArray())

        // remove a non-existing element
        h.remove(111)
        assertContentEquals(arrayListOf(null, 80, 75, 55, 60), h.heapArray())
    }

    private fun <T : Comparable<T>> createHeap(vararg items: T): Heap<T> {
        val h = Heap<T>()
        for (item in items) {
            h.insert(item)
        }
        return h
    }
}