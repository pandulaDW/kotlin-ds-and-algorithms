package datastructures

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LinkedListTest {
    private var list: LinkedList<Int>? = null
    private fun createTestList() = LinkedList(10, 20, 30)

    @BeforeEach
    fun setUp() {
        list = createTestList()
    }

    @Test
    fun prepend() {
        list?.prepend(101)
        list?.prepend(103)
        assertEquals(5, list?.size())
        assertEquals(103, list?.head()?.data)
    }

    @Test
    fun append() {
        list?.append(101)
        list?.append(103)
        assertEquals(5, list?.size())
        assertEquals(103, list?.tail()?.data)
    }

    @Test
    fun removeLast() {
        assertEquals(list?.removeLast()?.data, 30)
        assertEquals(2, list?.size())

        assertEquals(list?.removeLast()?.data, 20)
        assertEquals(1, list?.size())

        assertEquals(list?.removeLast()?.data, 10)
        assertEquals(0, list?.size())

        assertNull(list?.removeLast()?.data)
        assertEquals(0, list?.size())
    }

    @Test
    fun removeFirst() {
        assertEquals(list?.removeFirst()?.data, 10)
        assertEquals(2, list?.size())

        assertEquals(list?.removeFirst()?.data, 20)
        assertEquals(1, list?.size())

        assertEquals(list?.removeLast()?.data, 30)
        assertEquals(0, list?.size())

        assertNull(list?.removeLast()?.data)
        assertEquals(0, list?.size())
    }

    @Test
    fun get() {
        val exception = assertThrows<IndexOutOfBoundsException> { list?.set(10, 1111) }
        assertEquals("Index should be >= 0 and < 3", exception.message)

        assertEquals(10, list?.get(0)?.data)
        assertEquals(20, list?.get(1)?.data)
        assertEquals(30, list?.get(2)?.data)
    }

    @Test
    fun set() {
        list?.set(1, 333)
        list?.set(2, 400)
        assertEquals("[ 10 -> 333 -> 400 ]", list.toString())
    }

    @Test
    fun insert() {
        list?.insert(1, 50) // normal insert
        assertEquals("[ 10 -> 50 -> 20 -> 30 ]", list.toString())

        list?.insert(0, 60) // insert at the beginning
        assertEquals("[ 60 -> 10 -> 50 -> 20 -> 30 ]", list.toString())

        list?.insert(4, 90) // insert just before the last
        assertEquals("[ 60 -> 10 -> 50 -> 20 -> 90 -> 30 ]", list.toString())

        list?.insert(6, 110) // insert at the last element
        assertEquals("[ 60 -> 10 -> 50 -> 20 -> 90 -> 30 -> 110 ]", list.toString())

        list?.insert(3, 111) // sanity test
        assertEquals("[ 60 -> 10 -> 50 -> 111 -> 20 -> 90 -> 30 -> 110 ]", list.toString())
    }

    @Test
    fun remove() {
        assertThrows<IndexOutOfBoundsException> { list?.remove(10) }
        list?.remove(0)
        assertEquals("[ 20 -> 30 ]", list.toString())

        list = createTestList()
        list?.remove(1)
        assertEquals("[ 10 -> 30 ]", list.toString())

        list?.remove(1)
        assertEquals("[ 10 ]", list.toString())
    }

    @Test
    fun reverse() {
        list?.reverse()
        assertEquals("[ 30 -> 20 -> 10 ]", list.toString())

        list?.removeLast()
        list?.removeLast()
        list?.reverse()
        assertEquals("[ 30 ]", list.toString())

        list?.removeLast()
        list?.reverse()
        assertEquals("[  ]", list.toString())
    }

    @Test
    fun findMiddleNode() {
        var l = LinkedList(1)
        assertEquals(l.findMiddleNode(), 1)

        l = LinkedList(1, 2, 3, 4, 5)
        assertEquals(l.findMiddleNode(), 3)

        l.append(6)
        assertEquals(l.findMiddleNode(), 4)
    }

    @Test
    fun testToString() {
        val expected = "[ 10 -> 20 -> 30 ]"
        assertEquals(expected, list.toString())
    }
}