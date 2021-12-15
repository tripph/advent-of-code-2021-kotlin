fun main() {
    fun part1(input: List<String>): Int {
        val report = input.map { it.toInt() }
        return report.filterIndexed{ index, i ->
            println(i)
            if(index > 0) {
                i > report[index - 1]
            } else {
                false
            }
        }.count()
    }

    fun part2(input: List<String>): Int {
        val report = input.map { it.toInt() }
        var index = 0
        val groups = mutableListOf<List<Int>>()
        while(index < report.size) {
            if(report.subList(index, report.size).size >= 3) {
                groups.add(listOf(report[index], report[index+1], report[index+2]))
            }
            index++
        }
        return part1(groups.map { it.sum().toString() })
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val part1 = part1(testInput)
    check(part1 == 7)
    check(part2(testInput) == 5)
    val input = readInput("Day01")
    println(part1(input))
    println("Part 2: " + part2(input))
}
