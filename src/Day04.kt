typealias Board = List<List<Int>>

data class Game(val draws: List<Int>, val boards: MutableList<Board>)


fun main() {

    fun buildBoard(input: List<String>): Board {
        check(input.size == 5)
        return input.map { row ->
            val rowSplit = row.split(" ").filter { it.isNotBlank() }
            check(rowSplit.size == 5)
            rowSplit.map { it.toInt() }
        }
    }

    fun buildGame(input: List<String>): Game {
        val draws = input.first().split(",").map { it.toInt() }
        val boards = input.subList(2, input.size).filter { it.isNotBlank() }.chunked(5).map { buildBoard(it) }
        return Game(draws, boards.toMutableList())
    }

    fun findWinner(drawnNums: List<Int>, boards: List<Board>): List<Board> {
        if (drawnNums.size < 5) return emptyList()
        val acrossWinners = boards.filter { b ->
            b.any { r -> r.all { drawnNums.contains(it) } }
        }

        val vertWinners = boards.filter { b ->
            var winningColumnIndex: Int? = null
            b[0].forEachIndexed { index, _ ->
                if (listOf(b[0][index], b[1][index], b[2][index], b[3][index], b[4][index]).all { drawnNums.contains(it) })
                    winningColumnIndex = index
            }
            winningColumnIndex != null
        }

        return vertWinners + acrossWinners
    }

    fun calcScore(drawnNums: List<Int>, winner: Board): Int {
//        if(winner.flatten().size != winner.flatten().distinct().size) {
//            println("size and distinct size are different, meaning dupes on this board!")
//        }
        val unpickedSum = winner.flatten().filter { !drawnNums.contains(it) }.sum()
        return unpickedSum * drawnNums.last()
    }


    fun part1(input: List<String>): Int {
        val game = buildGame(input)
        val drawnNums = mutableListOf<Int>()
        var winners: MutableList<Board> = mutableListOf()
        game.draws.forEach { draw ->
            drawnNums.add(draw)
            val currentWinners = findWinner(drawnNums, game.boards)
            winners + currentWinners
            when (winners.isEmpty()) {
             true-> println("No Winner for drawnNums: (${drawnNums.joinToString()})")
                else -> {

//                    println("Winner on board #${game.boards.indexOf(winners) + 1}")
//                    return calcScore(drawnNums, winners!!)
                }
            }
        }
        TODO()
//        return calcScore(drawnNums, winners)
    }

    fun part2(input: List<String>): Int {
        val game = buildGame(input)
        val drawnNums = mutableListOf<Int>()
        var winners: MutableList<Board> = mutableListOf()
        var lastWinningDraw = mutableListOf<Int>()

        game.draws.forEachIndexed {index, draw ->
            drawnNums.add(draw)
            val currentWinners = findWinner(drawnNums, game.boards)
            winners += currentWinners
            println("[$index]: ${currentWinners.size} winners!")

            game.boards.removeAll(currentWinners)
            if(game.boards.isEmpty()) {
                lastWinningDraw = drawnNums
                return calcScore(lastWinningDraw, winners[winners.size - 2])
            }

        }
        return calcScore(lastWinningDraw, winners[winners.size - 2])
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val part2TestResult = part2(testInput)
    assert(part2TestResult == 1924)
//    val part1test = part1(testInput)
//    println("Part 1 TEST: $part1test")
//    check(part1test == 4512)
//    println("PART 2 TEST: ${part2(testInput)}")

    val input = readInput("Day04")
//    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
