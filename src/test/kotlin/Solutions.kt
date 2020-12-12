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

    @Test
    fun day4_1() {
        val required = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

        val validPasswords = File("Day 4/input").readText()
                .replace("\n\n", "|")
                .replace("\n", " ")
                .split("|")
                .map {
                    it.split(" ").map { it.split(":")[0] }.toSet()
                }.filter { it.containsAll(required) }

        println("Valid passwords ${validPasswords.size}")
    }

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

    @Test
    fun day4_2() {
        val validators: Map<String, (String) -> Boolean> = mapOf(
                "byr" to { it.toInt() in 1920..2002 },
                "iyr" to { it.toInt() in 2010..2020 },
                "eyr" to { it.toInt() in 2020..2030 },
                "hgt" to { it.toHeight().isValid() },
                "hcl" to { "^#\\p{XDigit}{6}\$".toRegex().matches(it) },
                "ecl" to { listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(it) },
                "pid" to { "^\\d{9}$".toRegex().matches(it) })

        val validPasswords = File("Day 4/input").readText()
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

    @Test
    fun day5_1() {
        val highest = File("Day 5/input").readLines()
                .map {
                    it.replace("B", "1")
                            .replace("F", "0")
                            .replace("R", "1")
                            .replace("L", "0")
                            .toInt(2)
                }.sortedDescending()[0]
        println(highest)
    }

    @Test
    fun day5_2() {
        val numbers = File("Day 5/input").readLines()
                .map {
                    it.replace("B", "1")
                            .replace("F", "0")
                            .replace("R", "1")
                            .replace("L", "0")
                            .toInt(2)
                }.sorted()

        out@ for (i in 0..(numbers.size - 2)) {
            if (numbers[i] + 2 == numbers[i + 1]) {
                println("Found ${numbers[i] + 1}")
                break@out
            }
        }
    }

    @Test
    fun day6_1() {
        val out = File("Day 6/input").readText()
                .replace("\n\n", "|")
                .replace("\n", "")
                .split("|")
                .map {
                    it.foldRight("", { v, acc -> if (acc.contains(v)) acc else acc + v }).length
                }.sum()

        println("$out")
    }

    @Test
    fun day6_2() {
        val out = File("Day 6/input").readText()
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

    data class ContainedBag(val name: String, val count: Int)
    data class Bag(val name: String, val containedBags: List<ContainedBag>, var totalBagCount: Int = 0)

    @Test
    fun day7_1() {
        val bags = File("Day 7/input").readLines()
                .map {
                    it.split(" contain ").let {
                        if (it[1] == "no other bags.") Bag(it[0].replace("bags", "bag"), emptyList())
                        else
                            Bag(it[0].replace("bags", "bag"), it[1].split(", ").map {
                                it.split(" ", limit = 2).let { ContainedBag(it[1].replace(".", "").replace("bags", "bag"), it[0].toInt()) }
                            })
                    }
                }

        println("$bags")
        val directBags = bags.filter { it.containedBags.any { it.name == "shiny gold bag" } }
                .map { it.name }

        var combined = directBags.toSet()

        while (true) {
            val derivedBags = bags.filter { it.containedBags.any { combined.contains(it.name) } }
                    .map { it.name }

            if (derivedBags.isEmpty()) {
                break;
            }

            val new = (combined + derivedBags).toSet()
            if (new.size == combined.size) {
                break;
            }
            combined = new
        }

        val out = combined.joinToString("\n")
        println(out)

        println(combined.size)
    }

    @Test
    fun day7_2() {
        val bags = File("Day 7/input").readLines()
                .map {
                    it.split(" contain ").let {
                        if (it[1] == "no other bags.") Bag(it[0].replace("bags", "bag"), emptyList())
                        else
                            Bag(it[0].replace("bags", "bag"), it[1].split(", ").map {
                                it.split(" ", limit = 2).let { ContainedBag(it[1].replace(".", "").replace("bags", "bag"), it[0].toInt()) }
                            })
                    }
                }.associateBy { it.name }

        bags.values.forEach { it.totalBagCount = countBags(bags, it.name) }

        println(bags.values.joinToString("\n"))

        println("Shiny: ${bags["shiny gold bag"]}")
    }

    fun countBags(bags: Map<String, Bag>, name: String): Int {
        val bag = bags[name]

        if (bag!!.containedBags.isEmpty()) return 0

        return bag.containedBags.map {
            (countBags(bags, it.name) + 1) * it.count
        }.sum()
    }

    data class Instruction(var op: String, val arg: Int, var executed: Boolean = false)
    data class ProgramState(var pos: Int, var acc: Int)

    @Test
    fun day8_1() {

        val program = File("Day 8/input").readLines()
                .map {
                    it.split(" ").let { parts ->
                        Instruction(parts[0], parts[1].toInt())
                    }
                }.toTypedArray()

        val state = executeProgram(program)

        println("Acc: ${state}, program size: ${program.size}")
    }

    private fun executeProgram(program: Array<Instruction>): ProgramState {
        val executor: (ProgramState, Instruction) -> ProgramState = { state, instr ->
            when (instr.op) {
                "nop" -> state.copy(pos = state.pos + 1)
                "acc" -> state.copy(pos = state.pos + 1, acc = state.acc + instr.arg)
                "jmp" -> state.copy(pos = state.pos + instr.arg)
                else -> throw IllegalStateException("Invalid instruction ${instr.op}")
            }
        }

        var state = ProgramState(0, 0)
        outer@ while (!program[state.pos].executed) {
            program[state.pos].executed = true
            state = executor(state, program[state.pos])
            if (state.pos == program.size) break@outer
        }
        return state
    }

    @Test
    fun day8_2() {

        var program = File("Day 8/input").readLines()
                .map {
                    it.split(" ").let { parts ->
                        Instruction(parts[0], parts[1].toInt())
                    }
                }.toTypedArray()

        outer@ for (i in program.indices) {
            program.forEach { it.executed = false }
            if (program[i].op == "nop") {
                program[i].op = "jmp"
                val state = executeProgram(program)
                if (state.pos == program.size) {
                    println("Found solution: $state with change nop -> jmp to $i")
                    break@outer
                }
                program[i].op = "nop"
            }
        }

        outer@ for (i in program.indices) {
            program.forEach { it.executed = false }
            if (program[i].op == "jmp") {
                program[i].op = "nop"
                val state = executeProgram(program);
                if (state.pos == program.size) {
                    println("Found solution: $state with change jmp -> nop to $i")
                    break@outer
                }
                program[i].op = "jmp"
            }
        }
    }

    @Test
    fun day9_1() {

        val numbers = File("Day 9/input").readLines()
                .map(String::toLong)

        println("Numbers: $numbers")
        var start = 0
        val preamble = 25

        while (start + preamble < numbers.size) {
            val toCheck = numbers.drop(start).take(preamble)
            val next = numbers[start + preamble] //.drop(start + preamble).

//            println("To check: $toCheck, next: $next")

            var match = false
            outer@ for (i in toCheck.indices) {
                for (j in toCheck.drop(i + 1)) {
                    if (toCheck[i] + j == next) {
                        match = true
                        break@outer
                    }
                }
            }

            if (!match) {
                println("No match for $next")
            }

            start++
        }
    }

    @Test
    fun day9_2() {

        val numbers = File("Day 9/input").readLines()
                .map(String::toLong)

        println("Numbers: $numbers")
        var start = 0

        outer@while (start < numbers.size) {
            var running: List<Long> = emptyList()
            var acc = 0L

            println("Checking ${numbers.drop(start)}")
            outer@ for (v in numbers.drop(start)) {
                running += v
                acc += v
                if (acc >= 15690279L) {
                    break@outer
                }
            }

            if (acc == 15690279L) {
                println("Found match on $start $running")

                println("Answer = ${running!!.min()?.plus(running.max() ?: 0)}")
                break@outer
            } else {
//                println("No match numbers $running")
            }

            start++
        }
    }

    @Test
    fun day10_1() {
        val numbers = File("Day 10/input").readLines()
                .map(String::toInt)
                .sorted()

        println(numbers)

        var prev = 0
        var ones = 0
        var threes = 0
        for (i in numbers) {
            if (i - prev == 1) {
                ones++
            } else if (i - prev == 3) {
                threes++
            } else throw IllegalStateException("fuck ${ones * threes} $i")

            prev = i
        }

        println(ones * (threes + 1))

    }

    val memoization = HashMap<List<Long>, Long>()

    fun variations(list: List<Long>): Long {
        if (!memoization.containsKey(list)) {
            memoization[list] = variations2(list)
        }

        return memoization[list]!!
    }

    fun variations2(list: List<Long>): Long {
        if (list.isEmpty()) {
            return 1
        }

        val first = list.first()
        val entries = list.drop(1).takeWhile { it <= first + 3 }.size

        if (entries == 0) {
            return variations(list.drop(1))
        }

        val v = (1..entries).map {
            variations(list.drop(it))
        }.fold(0L, { acc, v -> acc + v })

        return v
    }

    @Test
    fun day10_2() {
        val original = File("Day 10/input").readLines()
                .map(String::toLong)

        val numbers = (original + 0L)
                .sorted()

        println(variations(numbers))
    }
}