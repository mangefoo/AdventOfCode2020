import java.io.File

class Day4_2 : Puzzle() {

    override val day = 4

    data class Height(val height: Int, val scale: String) {
        fun isValid(): Boolean =
                when (scale) {
                    "cm" -> height in 150..193
                    "in" -> height in 59..76
                    else -> false
                }
    }

    fun String.toHeight(): Height =
            when {
                endsWith("cm") -> Height(substring(0, length - 2).toInt(), "cm")
                endsWith("in") -> Height(substring(0, length - 2).toInt(), "in")
                else -> Height(0, "invalid")
            }

    fun run() {
        val validators: Map<String, (String) -> Boolean> = mapOf(
                "byr" to { it.toInt() in 1920..2002 },
                "iyr" to { it.toInt() in 2010..2020 },
                "eyr" to { it.toInt() in 2020..2030 },
                "hgt" to { it.toHeight().isValid() },
                "hcl" to { "^#\\p{XDigit}{6}\$".toRegex().matches(it) },
                "ecl" to { listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(it) },
                "pid" to { "^\\d{9}$".toRegex().matches(it) })

        val validPasswords = content("input")
                .replace("\n\n", "|")
                .replace("\n", " ")
                .split("|")
                .map {
                    it.split(" ")
                            .map {
                                it.split(":").let { it[0] to it[1] }
                            }.filter {
                                validators[it.first]?.invoke(it.second) ?: false
                            }.map {
                                it.first
                            }.toSet()
                }
                .filter {
                    it.containsAll(validators.keys)
                }

        println("Valid passwords ${validPasswords.size}")
    }
}

fun main(args: Array<String>) {
    Day4_2().run()
}