class Day3_2 : Puzzle() {

    override val day = 3

    data class Day3Input(val right: Int, val down: Int)

    fun run() {
        val map = lines("input")

        val scenarios = listOf(Day3Input(1, 1), Day3Input(3, 1), Day3Input(5, 1), Day3Input(7, 1), Day3Input(1, 2))

        val total = scenarios.map {
            map.mapIndexed { i, row ->
                if (i % it.down == 0) row[((i * it.right) / it.down) % row.length]
                else '!'
            }.filter {
                it == '#'
            }.count()
        }.fold(1L, { a, b -> a * b })

        println("Trees hit: $total")
    }
}

fun main(args: Array<String>) {
    Day3_2().run()
}