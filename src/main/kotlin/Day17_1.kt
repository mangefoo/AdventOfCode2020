class Day17_1 : Puzzle() {

    override val day = 17

    var map: HashSet<List<Int>> = HashSet()

    private fun neighbours(x: Int, y: Int, z: Int): List<List<Int>> {
        var res = listOf<List<Int>>()
        for (xx in (x - 1)..(x + 1))
            for (yy in (y - 1)..(y + 1))
                for (zz in (z - 1)..(z + 1))
                    if (xx != x || yy != y || zz != z)
                        res += listOf(listOf(xx, yy, zz))
        return res
    }

    fun run() {
        lines("input").forEachIndexed { y, v ->
            v.forEachIndexed { x, c ->
                if (c == '#') map.add(listOf(x, y, 0))
            }
        }

        for (round in 1..6) {
            val newMap = HashSet<List<Int>>()
            val xRange = Pair((map.map { it[0] }.min() ?: 0) - 1, (map.map { it[0] }.max() ?: 0) + 1)
            val yRange = Pair((map.map { it[1] }.min() ?: 0) - 1, (map.map { it[1] }.max() ?: 0) + 1)
            val zRange = Pair((map.map { it[2] }.min() ?: 0) - 1, (map.map { it[2] }.max() ?: 0) + 1)

            for (x in (xRange.first)..(xRange.second)) {
                for (y in (yRange.first)..(yRange.second)) {
                    for (z in (zRange.first)..(zRange.second)) {
                        val matches = neighbours(x, y, z).filter {
                            map.contains(it)
                        }

                        val count = matches.size

                        val cords = listOf(x, y, z)

                        if (map.contains(cords) && (count == 3 || count == 2)) {
                            newMap.add(cords)
                        } else if (!map.contains(cords) && count == 3) {
                            newMap.add(cords)
                        }
                    }
                }
            }
            map = newMap
        }

        println(map.size)
    }
}

fun main(args: Array<String>) {
    Day17_1().run()
}