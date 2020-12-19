class Day5_2 : Puzzle() {

    override val day = 5

    fun run() {
        val numbers = lines("input")
                .map {
                    it.replace("B", "1")
                            .replace("F", "0")
                            .replace("R", "1")
                            .replace("L", "0")
                            .toInt(2)
                }.sorted()

        out@ for (i in 0..(numbers.size - 2)) {
            if (numbers[i] + 2 == numbers[i + 1]) {
                println("Found ${numbers[i] + 1}")
                break@out
            }
        }
    }
}

fun main(args: Array<String>) {
    Day5_2().run()
}