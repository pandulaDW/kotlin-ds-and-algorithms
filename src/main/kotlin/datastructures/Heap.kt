package datastructures

import java.lang.StringBuilder
import kotlin.math.max

class Heap<T : Comparable<T>>(private val sortOrder: SortOrder = SortOrder.Max) {
    private val heapArray = arrayListOf<T?>(null)
    private var size = 0

    fun insert(value: T) {
        heapArray.add(value)
        size += 1
        var currentIdx = heapArray.size - 1
        var parentIdx = parentIndex(currentIdx)

        while (parentIdx != 0 && isLessOrMore(heapArray[parentIdx]!!, value)) {
            swapIndices(currentIdx, parentIdx)
            currentIdx = parentIdx
            parentIdx = parentIndex(currentIdx)
        }
    }

    fun remove(value: T, index: Int? = null) {
        // find the idx position of the value if an index is not given
        var currentIdx = index ?: heapArray.indexOf(value)
        if (currentIdx == -1 || currentIdx == 0) return
        size = -1

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
        if (rootVal != null) remove(rootVal, 1)
    }

    fun heapArray(): ArrayList<T?> {
        return ArrayList(heapArray)
    }

    fun root(): T? {
        return heapArray.getOrNull(1)
    }

    private fun isLessOrMore(val1: T, val2: T): Boolean {
        if (sortOrder == SortOrder.Max) {
            return val1 <= val2
        }
        return val1 >= val2
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

    fun depth(): Int {
        var maxDepth = 0

        fun findDepth(currentIdx: Int, depth: Int) {
            maxDepth = max(maxDepth, depth + 1)

            // on initial visit to the node, we prefer the left
            if (heapArray.getOrNull(leftChildIdx(currentIdx)) != null) {
                findDepth(leftChildIdx(currentIdx), depth + 1)
            }
            // on subsequent visit to the node, we defer to the right
            if (heapArray.getOrNull(rightChildIdx(currentIdx)) != null) {
                findDepth(rightChildIdx(currentIdx), depth + 1)
            }
        }

        findDepth(1, 0)
        return maxDepth
    }

    override fun toString(): String {
        val buckets = arrayListOf<ArrayList<T>>()

        fun traverseNode(currentIdx: Int, depth: Int) {
            val applicableBucket = buckets.getOrNull(depth)
            if (applicableBucket != null) applicableBucket.add(heapArray[currentIdx]!!)
            else buckets.add(arrayListOf(heapArray[currentIdx]!!))

            // on initial visit to the node, we prefer the left
            if (heapArray.getOrNull(leftChildIdx(currentIdx)) != null) {
                traverseNode(leftChildIdx(currentIdx), depth + 1)
            }
            // on subsequent visit to the node, we defer to the right
            if (heapArray.getOrNull(rightChildIdx(currentIdx)) != null) {
                traverseNode(rightChildIdx(currentIdx), depth + 1)
            }
        }

        // populate the buckets
        traverseNode(1, 0)

        // consolidate the buckets in a string
        val s = StringBuilder()
        for (bucket in buckets.withIndex()) {
            for (item in bucket.value.withIndex()) {
                if (bucket.index == 0) {
                    s.append("${" ".repeat(9)}${item.value}")
                }
                if (bucket.index == 1) {
                    s.append("${" ".repeat(6)}${item.value}")
                }
                if (bucket.index == 2) {
                    s.append("${" ".repeat(3)}${item.value}")
                }
                if (bucket.index == 3) {
                    s.append("${" ".repeat(1)}${item.value}")
                }
            }
            s.appendLine()
        }

        return s.toString()
    }
}

fun <T : Comparable<T>> findKthSmallest(nums: ArrayList<T>, k: Int): T? {
    val h = Heap<T>(SortOrder.Min)
    for (v in nums) {
        h.insert(v)
    }

    var count = 0
    while (count < k - 1) {
        h.removeRoot()
        count += 1
    }

    return h.root()
}

fun <T : Comparable<T>> streamMax(nums: ArrayList<T>): ArrayList<T> {
    val h = Heap<T>(SortOrder.Max)
    val out = arrayListOf<T>()

    for (v in nums) {
        h.insert(v)
        out.add(h.root()!!)
    }

    return out
}