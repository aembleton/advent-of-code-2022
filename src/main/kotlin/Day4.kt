import java.io.File

fun main() {
    val input = File("src/main/resources/day4.txt").readLines()
    println("Part 1: ${Day4.part1(input)}")
    println("Part 2: ${Day4.part2(input)}")
}

object Day4 {

    fun part1(input:List<String>) = input.count {
        val (elf1,elf2) = it.split(",").map {rangeTxt -> rangeTxt.toRange().toSet() }
        elf1.containsAll(elf2) || elf2.containsAll(elf1)
    }

    private fun String.toRange() = this.split("-").map { it.toInt() }.let { it[0]..it[1] }

    fun part2(input:List<String>) = input.count {
        val (elf1,elf2) = it.split(",").map {rangeTxt -> rangeTxt.toRange().toSet() }
        elf1.intersect(elf2).isNotEmpty()
    }
}