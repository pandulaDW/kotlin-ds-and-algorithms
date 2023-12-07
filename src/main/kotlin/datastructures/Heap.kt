package datastructures

class Heap<T : Comparable<T>> {
    private val heapArray = arrayListOf<T?>(null)

    fun insert(value: T) {
        heapArray.add(value)
        var currentIdx = heapArray.size - 1
        var parentIdx = parentIndex(currentIdx)

        while (parentIdx != 0 && heapArray[parentIdx]!! < value) {
            swapIndices(currentIdx, parentIdx)
            currentIdx = parentIdx
            parentIdx = parentIndex(currentIdx)
        }
    }

    // Returns a copy of the underlying array
    fun heapArray(): ArrayList<T?> {
        return ArrayList(heapArray)
    }

    private fun swapIndices(index1: Int, index2: Int) {
        val temp = heapArray[index2]
        heapArray[index2] = heapArray[index1]
        heapArray[index1] = temp
    }

    private fun parentIndex(index: Int): Int {
        return index / 2
    }

    override fun toString(): String {
        return heapArray.toString()
    }
}