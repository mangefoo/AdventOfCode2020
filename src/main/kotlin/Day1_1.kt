class Day1_1 : Puzzle() {

    override val day = 1

    fun run() {
        val input = lines("input").map { it.toInt() }
        outer@ for (first in input) {
            for (second in input.drop(1)) {
                if (first + second == 2020) {
                    println("Result: ${first * second}")
                    break@outer
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    Day1_1().run()
}