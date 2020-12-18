class Day15_2: Puzzle() {

    override val day = 15

    fun run() {
        val input = listOf(0,20,7,16,1,18,15)

        val memory = HashMap<Int, Int>()
        var spoken = input.last()

        input.dropLast(1).forEachIndexed { i, v ->
            memory[v] = i + 1
        }

        println("$memory $spoken")

        (input.size until 30000000).forEach {
            val next = if (memory.containsKey(spoken)) it - memory[spoken]!! else 0
            memory[spoken] = it
            spoken = next
        }

        println(spoken)
    }
}

fun main(args: Array<String>) {
    Day15_2().run()
}