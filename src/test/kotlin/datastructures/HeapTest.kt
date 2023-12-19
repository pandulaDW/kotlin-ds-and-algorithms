package datastructures

import kotlin.test.*

class HeapTest {
    @Test
    fun insert() {
        // max heap insert
        var h = createHeap(SortOrder.Max, 99, 58, 72, 61, 55, 100)
        var expected = arrayListOf(null, 100, 61, 99, 58, 55, 72)
        assertContentEquals(expected, h.heapArray())

        // min heap insert
        h = createHeap(SortOrder.Min, 99, 58, 72, 61, 55, 100)
        expected = arrayListOf(null, 55, 58, 72, 99, 61, 100)
        assertContentEquals(expected, h.heapArray())
    }

    @Test
    fun removeMaxHeap() {
        // remove the root element
        val h = createHeap(SortOrder.Max, 95, 75, 55, 50, 80, 60, 65)
        h.removeRoot()
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

    @Test
    fun removeMinHeap() {
        // remove the root element
        val h = createHeap(SortOrder.Min, 95, 75, 55, 50, 80, 60, 65)
        h.removeRoot()
        assertContentEquals(arrayListOf(null, 55, 65, 60, 95, 80, 75), h.heapArray())

        // remove a middle element
        h.remove(65)
        assertContentEquals(arrayListOf(null, 55, 75, 60, 95, 80), h.heapArray())

        // remove a leaf element
        h.remove(95)
        assertContentEquals(arrayListOf(null, 55, 75, 60, 80), h.heapArray())

        // remove a non-existing element
        h.remove(111)
        assertContentEquals(arrayListOf(null, 55, 75, 60, 80), h.heapArray())
    }

    private fun <T : Comparable<T>> createHeap(sortOrder: SortOrder, vararg items: T): Heap<T> {
        val h = Heap<T>(sortOrder)
        for (item in items) {
            h.insert(item)
        }
        return h
    }
}