fun main() {
    fun parseSegment(input: String): LineSegment {
        val sides = input.split(" -> ")
        val start = sides[0].split(",")
        val end = sides[1].split(",")
        return LineSegment(Point(start[0].toInt(), start[1].toInt()),Point(end[0].toInt(), end[1].toInt()))
    }
    fun countIntersections(lines: List<LineSegment>): Map<Point, Int> {
        val points = lines.flatMap { it.coveredPoints() }
        val res = mutableMapOf<Point,Int>()
        points.forEach{ point ->
            res[point] = res.getOrPut(point) { 0 } + 1
        }
        return res
    }
    fun part1Test() {
        val input = readInput("Day05_test")
        val lines = input.map { parseSegment(it) }.filter { it.isValid() }
        val intersections = countIntersections(lines)
        val answer = intersections.filter { it.value > 1 }.size
        println("Part1Test: $answer")
        check(answer == 5)
    }
    fun part1() {
        val input = readInput("Day05")
        val lines = input.map { parseSegment(it) }.filter { it.isValid() }
        val intersections = countIntersections(lines)
        val answer = intersections.filter { it.value >= 2 }.size
        println("Part 1 answer: $answer")
    }
    part1Test()
    part1()
}

data class Point(val x: Int, val y: Int)
data class LineSegment(val start: Point, val end: Point) {
    /** only horizontal/vertical */
    fun isValid(): Boolean = start.x == end.x || start.y == end.y
    fun coveredPoints(): List<Point> {
        return if(start.x == end.x) {
            //vertical
            (start.y..end.y).map { Point(start.x, it) }
        } else {
            //horizontal
            (start.x..end.x).map { Point(start.y, it) }
        }
    }
}