class Day14_1 : Puzzle() {

    override val day = 14

    fun run() {
        val lines = lines("input")

        var andMask = 0L
        var orMask = 0L
        val mem = HashMap<Long, Long>()

        lines.forEach {
            val (op, arg) = it.split(" = ")

            if (op.startsWith("mem")) {
                val pos = op.filter(Char::isDigit).toLong()
                mem[pos] = (arg.toLong() and andMask) or orMask
            } else if (op == "mask") {
                andMask = 0L
                orMask = 0L
                for (i in arg.indices) {
                    val maskVal = if (arg[i] == 'X') 1L else 0L
                    val newVal = if (arg[i] == 'X' || arg[i] == '0') 0L else 1L
                    andMask = andMask or (maskVal shl (35 - i))
                    orMask = orMask or (newVal shl (35 - i))
                }
            }
        }

        println("${mem.values.sum()}")
    }
}

fun main(args: Array<String>) {
    Day14_1().run()
}