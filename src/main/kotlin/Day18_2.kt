import java.util.*

class Day18_2 : Puzzle() {

    override val day = 18

    fun run() {
        val result = lines("input")
                .map { it.replace(" ", "") }
                .map { evaluate(it) }

        println("Result: ${result.sum()}")
    }

    private fun getToken(input: String): String {

        if (input[0].isDigit()) return input.takeWhile { it.isDigit() }
        return input[0].toString()
    }

    private fun evaluate(expression: String): Long {

        val operator = Stack<String>()
        val output = Stack<String>()

        var exp = expression
        while (!exp.isEmpty()) {
            val token = getToken(exp)
            exp = exp.drop(token.length)
            when {
                token[0].isDigit() -> {
                    output.push(token)
                }
                token[0] == ')' -> {
                    while (operator.peek() != "(") output.push(operator.pop())
                    if (operator.pop() != "(") throw IllegalStateException("Unbalanced parenthesis")
                }
                token[0] == '(' -> {
                    operator.push(token)
                }
                else -> {
                    while (operator.isNotEmpty() && operator.peek() != "(" && operator.peek() == "+") {
                        output.push(operator.pop())
                    }
                    operator.push(token)
                }
            }

        }

        while (operator.isNotEmpty()) output.push(operator.pop())

        val evaluationStack = Stack<Long>()
        output.forEach {
            if (it[0].isDigit()) evaluationStack.push(it.toLong())
            else when (it) {
                "*" -> evaluationStack.push(evaluationStack.pop() * evaluationStack.pop())
                "+" -> evaluationStack.push(evaluationStack.pop() + evaluationStack.pop())
                else -> throw IllegalStateException("Unknown operator $it")
            }
        }

        if (evaluationStack.size != 1) throw IllegalStateException("Stack does not contain one element $evaluationStack")

        return evaluationStack.pop()
    }
}

fun main(args: Array<String>) {
    Day18_2().run()
}
