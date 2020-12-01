import java.io.File

val input = File("/Users/mager/devel/adventOfCode/Day 1/input").readLines().map {
    it.toInt()
}

input.forEach {
    find(it, input.drop(1))
}

fun find(value: Int, tail: List<Int>) {
    if (tail.isEmpty()) return

    if (value + tail[0] == 2020) {
        println(value * tail[0])
        return
    }

    find(value, tail.drop(1))
}
