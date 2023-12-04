package datastructures

import java.lang.IndexOutOfBoundsException

class HashMap(private val size: Int = 7) {
    private val dataMap = Array(size) { Node("", 0) }

    private data class Node(val key: String, val value: Int, var next: Node? = null) {
        fun isEmpty(): Boolean {
            return key.isEmpty()
        }
    }

    fun set(key: String, value: Int) {
        val hash = hash(key, size)
        val newNode = Node(key, value)
        if (dataMap[hash].isEmpty()) {
            dataMap[hash] = newNode
        } else {
            var current: Node? = dataMap[hash]
            while (current?.next != null) {
                current = current.next
            }
            current?.next = newNode
        }
    }

    fun get(key: String): Int? {
        val hash = hash(key, size)
        try {
            var current: Node? = dataMap[hash]
            while (current != null) {
                if (current.key == key) return current.value
                current = current.next
            }
            return null
        } catch (_: IndexOutOfBoundsException) {
            return null
        }
    }

    fun keys(): ArrayList<String> {
        val list: ArrayList<String> = arrayListOf()

        for (el in dataMap) {
            if (el.isEmpty()) {
                continue
            }
            var current: Node? = el;
            while (current != null) {
                list.add(current.key)
                current = current.next
            }
        }

        return list
    }

    companion object {
        fun hash(key: String, mapSize: Int): Int {
            var hash = 0
            val keyChars = key.toCharArray()
            for (char in keyChars) {
                val asciiValue: Int = char.code
                hash = (hash + asciiValue * 23) % mapSize
            }
            return hash
        }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for (el in dataMap.iterator().withIndex()) {
            if (el.value.isEmpty()) {
                continue
            }
            builder.append("${el.index}:\n")
            var current: Node? = el.value;
            while (current != null) {
                builder.append(" { \"${current.key}\" = ${current.value} }\n")
                current = current.next
            }
        }
        return builder.toString()
    }
}