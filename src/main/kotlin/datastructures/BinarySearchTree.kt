package datastructures

class BinarySearchTree(vararg initData: Int) {
    class Node(val data: Int, var left: Node? = null, var right: Node? = null)

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
        while (current != null) {
            if (data < current.data) {
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
}