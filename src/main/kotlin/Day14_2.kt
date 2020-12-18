class Day14_2 : Puzzle() {

    override val day = 14

    private fun generate(mask: String): List<String> {
        if (mask.isEmpty()) {
            return listOf(mask)
        }

        if (mask[0] == 'X') {
            return generate(mask.drop(1)).map { listOf("0$it", "1$it") }.flatten()
        }

        return generate(mask.drop(1)).map { listOf(mask[0] + it) }.flatten()
    }

    private fun Long.toBinaryString(): String =
            (35 downTo 0).fold("") { acc, i ->
                acc + if ((this and (1L shl i)) != 0L) "1" else "0"
            }

    private fun combine(pos: String, mask: String): String =
            (0..35).fold("") { acc, i ->
                acc + when {
                    mask[i] == 'X' -> 'X'
                    mask[i] == '0' -> pos[i]
                    mask[i] == '1' -> '1'
                    else -> throw IllegalStateException("Illegal character ${mask[i]}")
                }
            }

    fun run() {
        val lines = lines("input")

        var mask = "0".repeat(36)
        val mem = HashMap<Long, Long>()

        lines.forEach {
            val (op, arg) = it.split(" = ")

            if (op.startsWith("mem")) {
                val pos = op.filter(Char::isDigit).toLong()
                val combined = combine(pos.toBinaryString(), mask)

                generate(combined)
                        .map { binString -> binString.toLong(2) }
                        .forEach { memPos ->
                            mem[memPos] = arg.toLong()
                        }
            } else if (op == "mask") {
                mask = arg
            }
        }

        println("${mem.values.sum()}")
    }
}

fun main(args: Array<String>) {
    Day14_2().run()
}