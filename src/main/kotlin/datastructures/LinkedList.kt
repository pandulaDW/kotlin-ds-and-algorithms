package datastructures

class LinkedList<T>(vararg initData: T) {
    data class Node<T>(var data: T, var next: Node<T>? = null)

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var length = 0

    init {
        for (data in initData) {
            append(data)
        }
    }

    fun append(data: T) {
        val newNode = Node(data)
        if (length == 0) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
        length++
    }

    fun prepend(data: T) {
        val newNode = Node(data)
        if (length == 0) {
            head = newNode
            tail = newNode
        } else {
            newNode.next = head
            head = newNode
        }
        length++
    }

    fun removeLast(): Node<T>? {
        var current = head
        // find the node before the tail by using reference equality
        while (current != null && current.next !== tail) {
            current = current.next
        }

        val returned = tail
        tail = current
        tail?.next = null
        if (current == null) head = null
        length = if (length == 0) 0 else length - 1

        return returned
    }

    fun removeFirst(): Node<T>? {
        val newHead = head?.next
        val currentHead = head
        currentHead?.next = null
        head = newHead
        if (newHead == null) tail = null
        length = if (length == 0) 0 else length - 1
        return currentHead
    }

    fun get(index: Int): Node<T>? {
        if (index < 0 || index >= length) {
            throw IndexOutOfBoundsException("Index should be >= 0 and < $length")
        }
        var current = head
        repeat(index) {
            current = current?.next
        }
        return current
    }

    fun set(index: Int, value: T) {
        val node = get(index)
        node?.data = value
    }

    fun insert(index: Int, value: T) {
        if (index == 0) {
            prepend(value)
            return
        }
        val previous = get(index - 1)
        val newNode = Node(value, previous?.next)
        previous?.next = newNode
        length++
    }

    fun remove(index: Int) {
        if (index == 0) {
            removeFirst()
            return
        }
        val previous = get(index - 1)
        val current = previous?.next
        previous?.next = current?.next
        current?.next = null
        length--
    }

    fun reverse() {
        var current = head
        val oldHead = head
        var currentAfter = head?.next
        current?.next = null

        while (currentAfter != null) {
            val currentAfterNext = currentAfter.next
            currentAfter.next = current
            current = currentAfter
            currentAfter = currentAfterNext
        }

        head = current
        tail = oldHead
    }

    override fun toString(): String {
        val allData = arrayListOf<T>()
        var current = head

        while (current != null) {
            allData.add(current.data)
            current = current.next
        }

        return "[ ${allData.joinToString(" -> ")} ]"
    }

    fun length(): Int {
        return length
    }

    fun head(): Node<T>? {
        return head?.copy()
    }

    fun tail(): Node<T>? {
        return tail?.copy()
    }
}