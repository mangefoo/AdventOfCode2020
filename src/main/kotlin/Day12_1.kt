import java.io.File

class Day12_1 {
    data class State(var lon: Int, var lat: Int, var dir: Char)

    fun toDeg(dir: Char): Int =
            when (dir) {
                'N' -> 0
                'E' -> 90
                'S' -> 180
                'W' -> 270
                else -> throw IllegalStateException("Invalid direction $dir")
            }

    fun toDir(deg: Int): Char =
            "NESW"[((deg + 360) % 360) / 90]

    fun actor(action: Char, arg: Int, state: State): State {
        when (action) {
            'N' -> state.lon += arg
            'S' -> state.lon -= arg
            'E' -> state.lat += arg
            'W' -> state.lat -= arg
            'F' -> actor(state.dir, arg, state)
            'L' -> state.dir = toDir(toDeg(state.dir) - arg)
            'R' -> state.dir = toDir(toDeg(state.dir) + arg)
            else -> throw(IllegalStateException("asdf"))
        }

        return state
    }

    fun run() {
        val instr = File("Day 12/input")
                .readLines()

        var state = State(0, 0, 'E')

        instr.forEach {
            println("$it $state")
            var action = it[0]
            var arg = it.substring(1).toInt()

            actor(action, arg, state)
        }

        println(Math.abs(state.lon) + Math.abs(state.lat))
    }
}


fun main(args: Array<String>) {
    Day12_1().run()
}