class Day13_1 : Puzzle() {

    override val day = 13

    fun run() {
        val lines = lines("input")
        val time = lines[0].toInt()
        val schedules = lines[1].split(",")
                .filter { it != "x" }
                .map(String::toInt)

        val earliest = schedules.map {
            Pair(it, it - (time.rem(it)))
        }.sortedBy {
            it.second
        }.first()

        println(earliest.first * earliest.second)
    }
}

fun main(args: Array<String>) {
    Day13_1().run()
}