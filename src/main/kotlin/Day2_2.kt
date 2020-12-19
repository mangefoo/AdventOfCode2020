class Day2_2 : Puzzle() {

    override val day = 2

    data class Password(val min: Int, val max: Int, val char: Char, val password: String)

    fun run() {
        val verifier: (Password) -> Boolean = {
            val i1 = it.min - 1
            val i2 = it.max - 1

            ((it.password[i1] == it.char) && (it.password[i2] != it.char)) ||
                    ((it.password[i1] != it.char) && (it.password[i2] == it.char))

        }

        val count = lines("input").map {
            val line = it.split(" ")
            val rule = line[0].split("-")
            val char = line[1][0]
            val password = line[2]

            Password(rule[0].toInt(), rule[1].toInt(), char, password)
        }.filter(verifier).count()

        println("$count passwords match")
    }
}

fun main(args: Array<String>) {
    Day2_2().run()
}