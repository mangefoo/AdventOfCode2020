class Day5_1 : Puzzle() {

    override val day = 5

    fun run() {
        val highest = lines("input")
                .map {
                    it.replace("B", "1")
                            .replace("F", "0")
                            .replace("R", "1")
                            .replace("L", "0")
                            .toInt(2)
                }.sortedDescending()[0]
        println(highest)
    }
}

fun main(args: Array<String>) {
    Day5_1().run()
}