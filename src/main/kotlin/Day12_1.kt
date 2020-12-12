import kotlin.math.abs

class Day12_1 : Puzzle() {

    override val day = 12

    data class State(var lon: Int, var lat: Int, var dir: Char)

    private fun toDeg(dir: Char): Int =
            when (dir) {
                'N' -> 0
                'E' -> 90
                'S' -> 180
                'W' -> 270
                else -> throw IllegalStateException("Invalid direction $dir")
            }

    private fun toDir(deg: Int): Char =
            "NESW"[((deg + 360) % 360) / 90]

    private fun actor(action: Char, arg: Int, state: State): State {
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
        val state = State(0, 0, 'E')

        lines("input").forEach {
            println("$it $state")
            val action = it[0]
            val arg = it.substring(1).toInt()

            actor(action, arg, state)
        }

        println(abs(state.lon) + abs(state.lat))
    }
}


fun main(args: Array<String>) {
    Day12_1().run()
}