class Day9_1 : Puzzle() {

    override val day = 9

    fun run() {

        val numbers = lines("input")
                .map(String::toLong)

        println("Numbers: $numbers")
        var start = 0
        val preamble = 25

        while (start + preamble < numbers.size) {
            val toCheck = numbers.drop(start).take(preamble)
            val next = numbers[start + preamble] //.drop(start + preamble).

            var match = false
            outer@ for (i in toCheck.indices) {
                for (j in toCheck.drop(i + 1)) {
                    if (toCheck[i] + j == next) {
                        match = true
                        break@outer
                    }
                }
            }

            if (!match) {
                println("No match for $next")
            }

            start++
        }
    }
}

fun main(args: Array<String>) {
    Day9_1().run()
}