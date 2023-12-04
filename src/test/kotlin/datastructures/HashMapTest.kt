package datastructures

import kotlin.test.*
import org.junit.jupiter.api.BeforeEach

class HashMapTest {
    private var m: HashMap? = null

    @BeforeEach
    fun setUp() {
        m = HashMap()
        m?.set("nails", 10)
        m?.set("tile", 20)
        m?.set("lumber", 21)
        m?.set("bolts", 31)
        m?.set("screws", 35)
    }

    @Test
    fun get() {
        assertEquals(35, m?.get("screws"))
        assertEquals(31, m?.get("bolts"))
        assertEquals(10, m?.get("nails"))
        assertEquals(20, m?.get("tile"))
        assertEquals(21, m?.get("lumber"))
        assertNull(m?.get("table"))
    }

    @Test
    fun keys() {
        val expected = arrayListOf("bolts", "lumber", "nails", "screws", "tile")
        val actual = m?.keys()
        actual?.sort()
        assertContentEquals(expected, actual)
    }

    @Test
    fun hash() {
        val hash = HashMap.hash("juxtaposition", 7)
        assertEquals(5, hash)
    }
}