fun main(args: Array<String>) {
    Day12_2().run()
}

class Day12_2 : Puzzle() {

    override val day = 12

    data class Waypoint(var lon: Int, var lat: Int)
    data class State(var lon: Int, var lat: Int, var waypoint: Waypoint)

    private fun toDir(degIn: Int, waypoint: Waypoint): Waypoint =
        when ((degIn + 360) % 360) {
            90 -> Waypoint(-waypoint.lat, waypoint.lon)
            180 -> Waypoint(-waypoint.lon, -waypoint.lat)
            270 -> Waypoint(waypoint.lat, -waypoint.lon)
            else -> waypoint
        }

    fun run() {
        val state = State(0, 0, Waypoint(1, 10))

        lines("input").forEach {
            val action = it[0]
            val arg = it.substring(1).toInt()

            when (action) {
                'N' -> state.waypoint.lon += arg
                'S' -> state.waypoint.lon -= arg
                'E' -> state.waypoint.lat += arg
                'W' -> state.waypoint.lat -= arg
                'F' -> { state.lon += state.waypoint.lon * arg; state.lat += state.waypoint.lat * arg }
                'L' -> state.waypoint = toDir(-arg, state.waypoint)
                'R' -> state.waypoint = toDir(arg, state.waypoint)
                else -> throw(IllegalStateException("Invalid action $action"))
            }
        }

        println(Math.abs(state.lon) + Math.abs(state.lat))
    }
}