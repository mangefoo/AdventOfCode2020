
class Day13_2 : Puzzle() {

    override val day = 13

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

    fun run() {
        val lines = lines("example")
        val schedules = lines[1].split(",")
                .mapIndexed { i, v -> Pair(i, v) }
                .filter { it.second != "x" }
                .map { Pair(it.first, it.second.toLong()) }

        schedules.forEach {
            println("X + ${it.first} === 0 mod ${it.second}")
        }

        val aArray = schedules.map { -it.first.toLong() }
        val nArray = schedules.map { it.second }

        println(nArray)
        println(aArray)

        val res = chineseRemainder(nArray.toTypedArray(), aArray.toTypedArray())
        println(res)
    }
}

fun main(args: Array<String>) {
    Day13_2().run()
}