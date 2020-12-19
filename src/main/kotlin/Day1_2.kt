class Day1_2 : Puzzle() {

    override val day = 1

    fun run() {
        val input = lines("input").map { it.toInt() }

        outer@ for (first in input) {
            for (second in input.drop(1)) {
                for (third in input.drop(2)) {
                    if (first + second + third == 2020) {
                        println("Result: ${first * second * third}")
                        break@outer
                    }
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    Day1_2().run()
}