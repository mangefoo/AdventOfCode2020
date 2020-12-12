import java.io.File

abstract class Puzzle {

    abstract val day: Int

    fun lines(file: String): List<String> = File("Day $day/$file").readLines()

    fun content(file: String): String = File("Day $day/$file").readText()
}