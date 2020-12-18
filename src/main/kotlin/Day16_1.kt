class Day16_1 : Puzzle() {

    override val day = 16

    fun run() {
        val input = content("input")
                .split("\n\n")

        val ranges = input[0].split("\n").map {
            it.split(": ")[1].split(" or ")
        }.flatten()

        val invalidTickets = input[2].split(":")[1].replace("\n", ",").split(",").filter { it != "" }.map { it.toInt() }
                .filter {
                    !isInRange(it, ranges)
                }

        println("${invalidTickets.sum()}")
    }

    private fun isInRange(ticket: Int, ranges: List<String>): Boolean {
        return ranges.any {
            val range = it.split("-").map { it.trim().toInt() }

            (ticket >= range[0] && ticket <= range[1])
        }
    }
}

fun main(args: Array<String>) {
    Day16_1().run()
}