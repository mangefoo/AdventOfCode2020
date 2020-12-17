
class Day13_2 : Puzzle() {

    override val day = 13

    fun run() {
        val lines = lines("input")
        val schedules = lines[1].split(",")
                .mapIndexed { i, v -> Pair(i, v) }
                .filter { it.second != "x" }
                .map { Pair(it.first, it.second.toLong()) }

        schedules.forEach {
            println("X + ${it.first} === 0 mod ${it.second}")
        }

        val aValues = schedules.map { -it.first.toLong() }
        val nValues = schedules.map { it.second }

        println(nValues)
        println(aValues)

        val res = chineseRemainder(nValues.toTypedArray(), aValues.toTypedArray())
        println(res)
    }
}

fun main(args: Array<String>) {
    Day13_2().run()
}