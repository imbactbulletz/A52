package algorithms.greedy

/**
 * Задат jе низ целих броjева A. Потребно jе модификовати низ на следећи начин: бирамо неки индекс i
    и мењамо A[i] са −A[i], и понављамо оваj поступак K пута. Вратити наjвећу могућу суму низа након
    поменуте модификациjе.
    Улаз:
    A = [2, −3, −1, 5, −4], K = 2
    Излаз:
    13
 */


fun main() {
    greedyB()
}

fun greedyB() {
    val array = arrayOf(1, 2, 3, 4)
    val k = 3

    val maxSum = solutionGreedyB(array, k)
    println(maxSum)
}


// time(n * logn)
// space(8)
fun solutionGreedyB(array: Array<Int>, k: Int): Int {
    fun flipItemAtIndex(i: Int, array: Array<Int>) {
        array[i] = 0 - array[i]
    }

    array.sort() // time (n * logn), space (0)
    var currentIndex = 0  // space(1)
    var remainingIterations = k  // space(1)
    while (remainingIterations > 0) { // time(k * 4), space(0)
        if (array[currentIndex] < 0) {
            flipItemAtIndex(currentIndex, array)
            if (array[currentIndex + 1] < 0) {
                currentIndex++
            }
        } else flipItemAtIndex(currentIndex, array)
        remainingIterations--
    }

    // time(n), space(2)
    return array.fold(0, { acc, curr -> acc + curr })
}

