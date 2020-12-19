class Day10_2 : Puzzle() {

    override val day = 10

    private val memoization = HashMap<List<Long>, Long>()

    private fun variations(list: List<Long>): Long {
        if (!memoization.containsKey(list)) {
            memoization[list] = calcVariations(list)
        }

        return memoization[list]!!
    }

    private fun calcVariations(list: List<Long>): Long {
        if (list.isEmpty()) {
            return 1
        }

        val first = list.first()
        val entries = list.drop(1).takeWhile { it <= first + 3 }.size

        if (entries == 0) {
            return variations(list.drop(1))
        }

        val v = (1..entries).map {
            variations(list.drop(it))
        }.fold(0L, { acc, v -> acc + v })

        return v
    }

    fun run() {
        val original = lines("input")
                .map(String::toLong)

        val numbers = (original + 0L)
                .sorted()

        println(variations(numbers))
    }
}

fun main(args: Array<String>) {
    Day10_2().run()
}