class Day3_1 : Puzzle() {

    override val day = 3

    fun run() {
        val count = lines("input")
                .mapIndexed { i, row ->
                    row[(i * 3) % row.length]
                }.filter { it == '#' }
                .count()

        println("Trees hit: $count")
    }
}

fun main(args: Array<String>) {
    Day3_1().run()
}