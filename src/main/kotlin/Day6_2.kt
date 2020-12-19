class Day6_2 : Puzzle() {

    override val day = 6

    fun run() {
        val out = content("input")
                .replace("\n\n", "|")
                .replace("\n", " ")
                .split("|")
                .map {
                    it.split(" ").map(String::toSet)
                            .reduce { acc: Collection<Char>, v -> if (acc == null) v else v.intersect(acc) }.size
                }
                .sum()

        println("$out")
    }
}

fun main(args: Array<String>) {
    Day6_2().run()
}