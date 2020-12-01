import org.junit.jupiter.api.Test
import java.io.File

class Solutions {
    
    @Test
    fun day1_1() {
        val input = File("/Users/mager/devel/adventOfCode/Day 1/input").readLines().map {
            it.toInt()
        }

        Day1.puzzle1(input)
    }

    @Test
    fun day1_2() {
        val input = File("/Users/mager/devel/adventOfCode/Day 1/input").readLines().map {
            it.toInt()
        }

        Day1.puzzle2(input)
    }
}