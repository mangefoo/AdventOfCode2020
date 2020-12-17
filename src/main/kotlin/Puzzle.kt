import java.io.File

abstract class Puzzle {

    abstract val day: Int

    fun lines(file: String): List<String> = File("src/main/resources/Day $day/$file").readLines()

    fun content(file: String): String = File("src/main/resources/Day $day/$file").readText()

    fun multInv(a: Long, b: Long): Long {
        if (b == 1L) return 1L
        var aa = a
        var bb = b
        var x0 = 0L
        var x1 = 1L
        while (aa > 1L) {
            val q = aa / bb
            var t = bb
            bb = aa % bb
            aa = t
            t = x0
            x0 = x1 - q * x0
            x1 = t
        }
        if (x1 < 0) x1 += b
        return x1
    }

    fun chineseRemainder(n: Array<Long>, a: Array<Long>): Long {
        val prod = n.fold(1L) { acc, i -> acc * i }
        var sum = 0L
        for (i in n.indices) {
            val absA = if (a[i] >= 0) a[i] else n[i] + a[i]
            val p = prod / n[i]
            sum += absA * multInv(p, n[i]) * p
        }
        return sum % prod
    }
}