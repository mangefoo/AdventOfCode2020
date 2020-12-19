class Day4_1 : Puzzle() {

    override val day = 4

    fun run() {
        val required = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

        val validPasswords = content("input")
                .replace("\n\n", "|")
                .replace("\n", " ")
                .split("|")
                .map {
                    it.split(" ").map { it.split(":")[0] }.toSet()
                }.filter { it.containsAll(required) }

        println("Valid passwords ${validPasswords.size}")
    }
}

fun main(args: Array<String>) {
    Day4_1().run()
}