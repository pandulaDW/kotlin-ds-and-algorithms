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

    @Test
    fun depth() {
        var h = createHeap(SortOrder.Max, 4)
        assertEquals(1, h.depth())

        h = createHeap(SortOrder.Max, 14, 5, 1)
        assertEquals(2, h.depth())

        h = createHeap(SortOrder.Max, 90, 58, 72, 61, 55, 100, 101, 23, -10, 15, 33, 155, 98, 17)
        assertEquals(4, h.depth())

        h = createHeap(SortOrder.Min, 90, 58, 72, 61, 55, 100, 101, 23, -10, 15, 33, 155, 98, 17)
        assertEquals(4, h.depth())
    }

    @Test
    fun findKthSmallest() {
        var actual = findKthSmallest(arrayListOf(7, 10, 4, 3, 20, 15), 3)
        assertEquals(7, actual)

        actual = findKthSmallest(arrayListOf(2, 1, 3, 5, 6, 4), 2)
        assertEquals(2, actual)

        actual = findKthSmallest(arrayListOf(9, 3, 2, 11, 7, 10, 4, 5), 5)
        assertEquals(7, actual)

        actual = findKthSmallest(arrayListOf(9), 1)
        assertEquals(9, actual)

        actual = findKthSmallest(arrayListOf(9, 9, 10), 2)
        assertEquals(9, actual)
    }

    @Test
    fun streamMax() {
        var actual = streamMax(arrayListOf(1, 5, 2, 9, 3, 6, 8))
        assertContentEquals(arrayListOf(1, 5, 5, 9, 9, 9, 9), actual)

        actual = streamMax(arrayListOf(10, 2, 5, 1, 0, 11, 6))
        assertContentEquals(arrayListOf(10, 10, 10, 10, 10, 11, 11), actual)

        actual = streamMax(arrayListOf(3, 3, 3, 3, 3))
        assertContentEquals(arrayListOf(3, 3, 3, 3, 3), actual)
    }

    private fun <T : Comparable<T>> createHeap(sortOrder: SortOrder, vararg items: T): Heap<T> {
        val h = Heap<T>(sortOrder)
        for (item in items) {
            h.insert(item)
        }
        return h
    }
}