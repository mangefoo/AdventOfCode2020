import org.junit.jupiter.api.Test
import java.io.File

class Solutions {

    @Test
    fun day1_1() {
        val input = File("Day 1/input").readLines().map {
            it.toInt()
        }

        outer@ for (first in input) {
            for (second in input.drop(1)) {
                if (first + second == 2020) {
                    println("Result: ${first * second}")
                    break@outer
                }
            }
        }
    }

    @Test
    fun day1_2() {
        val input = File("Day 1/input").readLines().map {
            it.toInt()
        }

        outer@ for (first in input) {
            for (second in input.drop(1)) {
                for (third in input.drop(2)) {
                    if (first + second + third == 2020) {
                        println("Result: ${first * second * third}")
                        break@outer
                    }
                }
            }
        }
    }

    data class Password(val min: Int, val max: Int, val char: Char, val password: String)

    @Test
    fun day2_1() {
        val verifier: (Password) -> Boolean = {
            val count = it.password.chars().filter(it.char.toInt()::equals).count()
            count >= it.min && count <= it.max
        }

        val count = File("Day 2/input").readLines().map {
            val line = it.split(" ")
            val rule = line[0].split("-")
            val char = line[1][0]
            val password = line[2]

            Password(rule[0].toInt(), rule[1].toInt(), char, password)
        }.filter(verifier).count()

        println("$count passwords match")
    }

    @Test
    fun day2_2() {
        val verifier: (Password) -> Boolean = {
            val i1 = it.min - 1
            val i2 = it.max - 1

            ((it.password[i1] == it.char) && (it.password[i2] != it.char)) ||
                    ((it.password[i1] != it.char) && (it.password[i2] == it.char))

        }

        val count = File("Day 2/input").readLines().map {
            val line = it.split(" ")
            val rule = line[0].split("-")
            val char = line[1][0]
            val password = line[2]

            Password(rule[0].toInt(), rule[1].toInt(), char, password)
        }.filter(verifier).count()

        println("$count passwords match")
    }

    @Test
    fun day3_1() {
        val count = File("Day 3/input").readLines()
                .mapIndexed { i, row ->
                    row[(i * 3) % row.length]
                }.filter { it == '#' }
                .count()

        println("Trees hit: $count")
    }

    data class Day3Input(val right: Int, val down: Int)

    @Test
    fun day3_2() {
        val map = File("Day 3/input").readLines()

        val scenarios = listOf(Day3Input(1, 1), Day3Input(3, 1), Day3Input(5, 1), Day3Input(7, 1), Day3Input(1, 2))

        val total = scenarios.map {
            map.mapIndexed { i, row ->
                if (i % it.down == 0) row[((i * it.right) / it.down) % row.length]
                else '!'
            }.filter {
                it == '#'
            }.count()
        }.fold(1L, { a, b -> a * b })

        println("Trees hit: $total")
    }
}