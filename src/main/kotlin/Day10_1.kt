class Day10_1 : Puzzle() {

    override val day = 10

    fun run() {
        val numbers = lines("input")
                .map(String::toInt)
                .sorted()

        var prev = 0
        var ones = 0
        var threes = 0
        for (i in numbers) {
            if (i - prev == 1) {
                ones++
            } else if (i - prev == 3) {
                threes++
            } else throw IllegalStateException("${ones * threes} $i")

            prev = i
        }

        println(ones * (threes + 1))

    }
}

fun main(args: Array<String>) {
    Day10_1().run()
}