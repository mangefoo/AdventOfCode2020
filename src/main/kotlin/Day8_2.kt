class Day8_2 : Puzzle() {

    override val day = 8

    data class Instruction(var op: String, val arg: Int, var executed: Boolean = false)
    data class ProgramState(var pos: Int, var acc: Int)

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

    fun run() {

        var program = lines("input")
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
}

fun main(args: Array<String>) {
    Day8_2().run()
}