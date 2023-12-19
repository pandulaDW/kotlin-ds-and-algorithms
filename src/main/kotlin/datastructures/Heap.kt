package datastructures

class Heap<T : Comparable<T>>(private val sortOrder: SortOrder = SortOrder.Max) {
    private val heapArray = arrayListOf<T?>(null)

    fun insert(value: T) {
        heapArray.add(value)
        var currentIdx = heapArray.size - 1
        var parentIdx = parentIndex(currentIdx)

        while (parentIdx != 0 && isLessOrMore(heapArray[parentIdx]!!, value)) {
            swapIndices(currentIdx, parentIdx)
            currentIdx = parentIdx
            parentIdx = parentIndex(currentIdx)
        }
    }

    fun remove(value: T) {
        // find the idx position of the value
        var currentIdx = heapArray.indexOf(value)
        if (currentIdx == -1 || currentIdx == 0) return

        // swap this value with the last item and pop this value off the heap
        swapIndices(currentIdx, heapArray.size - 1)
        heapArray.removeLast()

        // balance the tree
        while (true) {
            val left = heapArray.getOrNull(leftChildIdx(currentIdx))
            val leftPair = if (left != null) Pair(left, leftChildIdx(currentIdx)) else null

            val right = heapArray.getOrNull(rightChildIdx(currentIdx))
            val rightPair = if (right != null) Pair(right, rightChildIdx(currentIdx)) else null

            val max =
                if (leftPair != null && rightPair != null && isLessOrMore(leftPair.first, rightPair.first)) rightPair
                else leftPair ?: rightPair

            if (max != null && heapArray.getOrNull(currentIdx) != null && isLessOrMore(
                    heapArray[currentIdx]!!,
                    max.first
                )
            ) {
                swapIndices(currentIdx, max.second)
                currentIdx = max.second
            } else {
                return
            }
        }
    }

    fun removeRoot() {
        val rootVal = heapArray.getOrNull(1)
        if (rootVal != null) remove(rootVal)
    }

    fun heapArray(): ArrayList<T?> {
        return ArrayList(heapArray)
    }

    private fun isLessOrMore(val1: T, val2: T): Boolean {
        if (sortOrder == SortOrder.Max) {
            return val1 < val2
        }
        return val1 > val2
    }

    private fun swapIndices(index1: Int, index2: Int) {
        val temp = heapArray[index2]
        heapArray[index2] = heapArray[index1]
        heapArray[index1] = temp
    }

    private fun parentIndex(index: Int): Int {
        return index / 2
    }

    private fun leftChildIdx(parentIdx: Int): Int {
        return parentIdx * 2
    }

    private fun rightChildIdx(parentIdx: Int): Int {
        return (parentIdx * 2) + 1
    }

    override fun toString(): String {
        val buf = StringBuilder()
        var totalDepth = 0

        fun construct(currentIdx: Int, currentDepth: Int) {
            totalDepth += 1
            val currentDepthTemp = currentDepth + 1 // kotlin: no mutable fn params

            val leftChild = leftChildIdx(currentIdx)
            if (leftChild < heapArray.size) {
                construct(leftChild, currentDepthTemp)
            }

            buf.append("${" ".repeat(totalDepth - currentDepth)}${heapArray[currentIdx]}")

            val rightChild = rightChildIdx(currentIdx)
            if (rightChild < heapArray.size) {
                construct(rightChild, currentDepthTemp)
            }
        }

        construct(1, 1)

        return buf.toString()
    }
}