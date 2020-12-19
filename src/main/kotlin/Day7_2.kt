class Day7_2 : Puzzle() {

    override val day = 7

    data class ContainedBag(val name: String, val count: Int)
    data class Bag(val name: String, val containedBags: List<ContainedBag>, var totalBagCount: Int = 0)

    private fun countBags(bags: Map<String, Bag>, name: String): Int {
        val bag = bags[name]

        if (bag!!.containedBags.isEmpty()) return 0

        return bag.containedBags.map {
            (countBags(bags, it.name) + 1) * it.count
        }.sum()
    }

    fun run() {
        val bags = lines("input")
                .map {
                    it.split(" contain ").let {
                        if (it[1] == "no other bags.") Bag(it[0].replace("bags", "bag"), emptyList())
                        else
                            Bag(it[0].replace("bags", "bag"), it[1].split(", ").map {
                                it.split(" ", limit = 2).let { ContainedBag(it[1].replace(".", "").replace("bags", "bag"), it[0].toInt()) }
                            })
                    }
                }.associateBy { it.name }

        bags.values.forEach { it.totalBagCount = countBags(bags, it.name) }

        println(bags.values.joinToString("\n"))

        println("Shiny bags: ${bags["shiny gold bag"]}")
    }
}

fun main(args: Array<String>) {
    Day7_2().run()
}