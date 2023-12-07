package datastructures

import kotlin.test.*

class HeapTest {
    @Test
    fun insert() {
        val h = Heap<Int>()
        h.insert(99)
        h.insert(58)
        h.insert(72)
        h.insert(61)
        h.insert(55)
        h.insert(100)

        val expected = arrayListOf(null, 100, 61, 99, 58, 55, 72)
        assertContentEquals(expected, h.heapArray())
    }
}