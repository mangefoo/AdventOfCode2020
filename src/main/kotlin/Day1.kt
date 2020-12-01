class Day1 {
    companion object {
        fun puzzle1(input: List<Int>) {
            input.map { find(it, input) }
                    .first { it }
        }

        fun puzzle2(input: List<Int>) {
            input.map { find2(it, input, input.drop(1)) }
        }

        private fun find(value: Int, tail: List<Int>): Boolean {
            if (tail.isEmpty()) return false

            if (value + tail[0] == 2020) {
                println(value * tail[0])
                return true
            }

            return find(value, tail.drop(1))
        }

        private fun find2(value: Int, firstTail: List<Int>, secondTail: List<Int>): Boolean {
            if (firstTail.isEmpty()) return false;

            if (secondTail.isEmpty()) return find2(firstTail[0], firstTail.drop(1), firstTail);

            if (value + firstTail[0] + secondTail[0] == 2020) {
                println(value * firstTail[0] + secondTail[0])
            }

            return find2(value, firstTail, secondTail.drop(1))
        }
    }
}