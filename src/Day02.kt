enum class NavDirection(val s: String) {
    forward("forward"),
    down("down"),
    up("up"),
}
data class NavCommand(val direction: NavDirection, val units: Int)

fun main() {
    fun part1(input: List<String>): Int {
        val commands = input.map {
            val s = it.split(" ")
            NavCommand(NavDirection.valueOf(s[0]), s[1].toInt())
        }
        var horizPos = 0
        var depthPos = 0
        var aim = 0
        commands.forEach{
            when(it.direction) {
                NavDirection.forward -> {
                    horizPos += it.units
                    depthPos += aim * it.units
                }
                NavDirection.down -> aim += it.units
                NavDirection.up -> aim -= it.units
            }
        }
        return horizPos * depthPos
    }

    fun part2(input: List<String>): Int {
        TODO() //ACTUALLY JUST CHANGED PART 1 TO HANDLE PART 2 too
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val part1test = part1(testInput)
    println(part1test)
    check(part1test == 900)
//    check(part2(testInput) == 5)
    val input = readInput("Day02")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
