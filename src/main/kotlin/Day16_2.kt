class Day16_2 : Puzzle() {

    override val day = 16

    fun run() {
        val input = content("input")
                .split("\n\n")

        val ranges = input[0].split("\n").map {
            val tmp = it.split(": ")
            Pair(tmp[0], tmp[1].split(" or "))
        }
        val myTicket = input[1].split("\n")[1].split(",").map { it.toInt() }
        val nearbyTickets = input[2].split(":\n")[1].split("\n").filter { it != "" }.map { it.split(",").map { it.toInt() } }
        val allTickets = listOf(myTicket) + nearbyTickets

        val validTickets = allTickets.filter { ticket ->
            ticket.all { ticket ->
                ranges.map { it.second }.any { range ->
                    isInRange(ticket, range)
                }
            }
        }

        val columns = validTickets[0].indices.map { i ->
            validTickets.map { it[i] }
        }

        val memory = HashMap<String, Int>()
        val summary = columns.indices.map { colIndex ->
            val ranges = columnMatch(columns[colIndex], ranges)
            Pair(colIndex, ranges)
        }.sortedBy { it.second.size } // There's a pattern in the matching ranges where the number of matches is 1-20

        summary.forEach {
            if (it.second.isNotEmpty()) {
                val range = it.second.firstOrNull { !memory.containsKey(it) }
                if (range != null) {
                    memory[range] = it.first
                }
            }
        }

        val left = memory.filter { it.key.startsWith("departure") }
        val myTicketRows = left.map { myTicket[it.value] }

        println(myTicketRows.map{ it.toLong()}.reduce { acc: Long, v: Long -> acc * v })
    }

    private fun columnMatch(column: List<Int>, ranges: List<Pair<String, List<String>>>): List<String> =
        ranges.filter { range ->
            column.all { isInRange(it, range.second) }
        }.map {
            it.first
        }

    private fun isInRange(ticket: Int, ranges: List<String>): Boolean {
        return ranges.any {
            val range = it.split("-").map { it.trim().toInt() }

            (ticket >= range[0] && ticket <= range[1])
        }
    }
}

fun main(args: Array<String>) {
    Day16_2().run()
}