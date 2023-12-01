package datastructures

import kotlin.test.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class BinarySearchTreeTest {
    private var tree: BinarySearchTree? = null

    @BeforeEach
    fun setUp() {
        tree = BinarySearchTree(10, 8, 15, 1)
    }

    @Test
    fun insert() {
        tree?.insert(21)
        val expected = """{"data":10,"left":{"data":8,"left":{"data":1}},"right":{"data":15,"right":{"data":21}}}"""
        assertEquals(expected, tree.toString())
    }

    @Test
    fun contains() {
        assertTrue { tree?.contains(10)!! }
        assertTrue { tree?.contains(15)!! }
        assertTrue { tree?.contains(1)!! }
        assertFalse { tree?.contains(101)!! }
    }

    @Test
    fun string() {
        val expected = """{"data":10,"left":{"data":8,"left":{"data":1}},"right":{"data":15}}"""
        assertEquals(expected, tree.toString())
    }
}