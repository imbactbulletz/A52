fun main() {
    val array = arrayOf(1, 0, 2, 5, 4,1, 0, 2, 5, 4,1, 0, 2, 5, 4,1, 0, 2, 5, 4,1, 0, 2, 5, 4,1, 0, 2, 5, 4)

    quickSort(array)

    array.forEach { println(it) }
}

fun quickSort(array: Array<Int>) {
    fun sortInternal(startIndex: Int, endIndex: Int, array: Array<Int>) {
        when (endIndex - startIndex) {
            0 -> { } // no items to sort, same indexes
            1 -> if (array[startIndex] > array[endIndex]) swapIndexValues(startIndex, endIndex, array) // two items, trivial swap
            else -> {
                val updatedPivotIndex = partition(startIndex, endIndex, array)
                if (updatedPivotIndex != startIndex) {
                    sortInternal(startIndex, updatedPivotIndex - 1, array)
                }
                if (updatedPivotIndex != endIndex) {
                    sortInternal(updatedPivotIndex + 1, endIndex, array)
                }
            }
        }
    }

    val startIndex = 0
    val endIndex = array.size - 1

    // do not sort arrays with 1 item
    if (array.size == 1) {
        return
    } else {
        sortInternal(startIndex, endIndex, array)
    }
}


/**
 * Reorders the array in place in such order that all items which are lesser than pivot (item at startIndex) are to the left
 * of the pivot, and all items which are greater than pivot are to the right.
 *
 * @param startIndex index from which the array will be reordered
 * @param endIndex index to which the array will be reordered
 * @param array the array upon which the reorder will be performed
 */
fun partition(startIndex: Int, endIndex: Int, array: Array<Int>): Int {
    val pivot = array[startIndex]

    var leftIndex = findIndexOfFirstItemGreaterThan(pivot, startIndex, endIndex, array)
    var rightIndex = findIndexOfLastItemLesserThanOrEquals(pivot, startIndex, endIndex, array)

    while (leftIndex < rightIndex) {
        if (leftIndex == NOT_FOUND || rightIndex == NOT_FOUND) break

        swapIndexValues(leftIndex, rightIndex, array)

        leftIndex = findIndexOfFirstItemGreaterThan(pivot, ++leftIndex, endIndex, array)
        rightIndex = findIndexOfLastItemLesserThanOrEquals(pivot, startIndex, --rightIndex, array)
    }

    return if (rightIndex != NOT_FOUND) {
        swapIndexValues(startIndex, rightIndex, array)
        rightIndex
    } else {
        NOT_FOUND
    }
}

fun findIndexOfFirstItemGreaterThan(value: Int, startIndex: Int, endIndex: Int, array: Array<Int>): Int {
    require(startIndex < array.size - 1 && endIndex < array.size)
    require(startIndex < endIndex)

    var currentIndex = startIndex
    while (array[currentIndex] <= value && currentIndex < endIndex) {
        currentIndex++
    }

    return if (array[currentIndex] > value) currentIndex else NOT_FOUND
}

fun findIndexOfLastItemLesserThanOrEquals(value: Int, startIndex: Int, endIndex: Int, array: Array<Int>): Int {
    require(endIndex < array.size)
    require(endIndex > startIndex)

    var currentIndex = endIndex
    while (array[currentIndex] > value) {
        currentIndex--
    }

    return if (array[currentIndex] <= value) currentIndex else NOT_FOUND
}

fun swapIndexValues(firstIndex: Int, secondIndex: Int, array: Array<Int>) {
    val tmp = array[firstIndex]
    array[firstIndex] = array[secondIndex]
    array[secondIndex] = tmp
}

const val NOT_FOUND = -1
