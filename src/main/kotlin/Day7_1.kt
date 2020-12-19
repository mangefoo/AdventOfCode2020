class Day7_1 : Puzzle() {

    override val day = 7

    data class ContainedBag(val name: String, val count: Int)
    data class Bag(val name: String, val containedBags: List<ContainedBag>, var totalBagCount: Int = 0)

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
                }

        val directBags = bags.filter { it.containedBags.any { it.name == "shiny gold bag" } }
                .map { it.name }

        var combined = directBags.toSet()

        while (true) {
            val derivedBags = bags.filter { it.containedBags.any { combined.contains(it.name) } }
                    .map { it.name }

            if (derivedBags.isEmpty()) {
                break;
            }

            val new = (combined + derivedBags).toSet()
            if (new.size == combined.size) {
                break;
            }
            combined = new
        }

        println(combined.size)
    }
}

fun main(args: Array<String>) {
    Day7_1().run()
}