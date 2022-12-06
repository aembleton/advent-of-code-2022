import java.io.File

fun main() {
    val input = File("src/main/resources/day3.txt").readLines()
    println("Part 1: ${Day3.part1(input)}")
    println("Part 2: ${Day3.part2(input)}")
}

object Day3 {
    fun part1(input:List<String>) = input.sumOf { appearsInBothCompartments(it).toPriority() }

    private fun appearsInBothCompartments(input:String):Char {
        val left = input.substring(0, input.length / 2).toSet()
        val right = input.substring(input.length / 2).toSet()
        return left.intersect(right).first()
    }

    private fun Char.toPriority() = when(this) {
        in 'a'..'z' -> this.code - 'a'.code + 1
        in 'A'..'Z' -> this.code - 'A'.code + 27
        else -> throw IllegalArgumentException("Invalid character: $this")
    }

    fun part2(input:List<String>) = input
        .chunked(3)
        .sumOf { uniqueChar(it).toPriority() }

    fun uniqueChar(input:List<String>):Char {
        val (a,b,c) = input.map { it.toSet() }
        return a.intersect(b).intersect(c).first()
    }
}