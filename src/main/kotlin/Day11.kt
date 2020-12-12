import java.io.File

fun getAt(map: List<List<Char>>, x: Int, y: Int, xDir: Int, yDir: Int): Char {
    return if (x < 0 || x >= map[0].size || y < 0 || y >= map.size)
        map[y - yDir][x - xDir]
    else if (map[y][x] == '.') getAt(map, x + xDir, y + yDir, xDir, yDir)
    else map[y][x]
}

fun occupied(map: List<List<Char>>, x: Int, y: Int): Int {
    return ((x-1)..(x+1)).map { xx ->
        ((y-1)..(y+1)).map { yy ->
            if ((xx >= 0 && yy >= 0) &&
                    (xx != x || yy != y) &&
                    (xx < map[0].size && yy < map.size)) {
                getAt(map, xx, yy, xx - x, yy - y) == '#'
            }  else false
        }
    }
            .flatten()
            .filter { it }
            .size
}

private fun run(map: List<List<Char>>): List<List<Char>> =
    map.mapIndexed { y, row ->
        row.mapIndexed { x, chair ->
            when (chair) {
                '#' -> if (occupied(map, x, y) >= 5) 'L' else '#'
                'L' -> if (occupied(map, x, y) == 0) '#' else 'L'
                else -> chair
            }
        }
    }

fun main(args: Array<String>) {
    val map = File("Day 11/input")
            .readLines()
            .map { it.toList() }

    var last = map
    var current = map
    var rounds = 0
    do {
        last = current
        current = run(last)
        rounds++
    } while(last != current)

    println("Occupied seats ${current.flatten().filter { it == '#' }.size}")
}
