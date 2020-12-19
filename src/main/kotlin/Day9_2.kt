class Day9_2 : Puzzle() {

    override val day = 9

    fun run() {

        val numbers = lines("input")
                .map(String::toLong)

        var start = 0

        outer@while (start < numbers.size) {
            var running: List<Long> = emptyList()
            var acc = 0L

            println("Checking ${numbers.drop(start)}")
            outer@ for (v in numbers.drop(start)) {
                running += v
                acc += v
                if (acc >= 15690279L) {
                    break@outer
                }
            }

            if (acc == 15690279L) {
                println("Found match on $start $running")

                println("Answer = ${running!!.min()?.plus(running.max() ?: 0)}")
                break@outer
            }

            start++
        }
    }
}

fun main(args: Array<String>) {
    Day9_2().run()
}