package datastructures

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class BinarySearchTree(vararg initData: Int) {
    @Serializable
    data class Node(val data: Int, var left: Node? = null, var right: Node? = null)

    private var root: Node? = null;

    init {
        for (data in initData) {
            insert(data)
        }
    }

    fun insert(data: Int) {
        val newNode = Node(data)
        if (root == null) {
            root = newNode
            return
        }

        var current = root
        while (true) {
            if (data == current?.data) {
                return
            } else if (data < current?.data!!) {
                if (current.left == null) {
                    current.left = newNode
                    break
                } else {
                    current = current.left
                }
            } else {
                if (current.right == null) {
                    current.right = newNode
                    break
                } else {
                    current = current.right
                }
            }
        }
    }

    fun contains(data: Int): Boolean {
        var current = root
        while (current != null) {
            current = if (data == current.data) {
                return true
            } else if (data < current.data) {
                current.left
            } else {
                current.right
            }
        }
        return false
    }

    override fun toString(): String {
        return Json.encodeToString(root)
    }
}