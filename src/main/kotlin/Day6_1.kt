class Day6_1 : Puzzle() {

    override val day = 6

    fun run() {
        val out = content("input")
                .replace("\n\n", "|")
                .replace("\n", "")
                .split("|")
                .map {
                    it.foldRight("", { v, acc -> if (acc.contains(v)) acc else acc + v }).length
                }.sum()

        println("$out")
    }
}

fun main(args: Array<String>) {
    Day6_1().run()
}