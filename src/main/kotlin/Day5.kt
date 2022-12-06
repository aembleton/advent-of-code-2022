import java.io.File

fun main() {
    val input = File("src/main/resources/day5.txt").readLines()
    val stacksInput = input.filter { it.contains("[") }
    val numberOfStacks = (stacksInput.maxByOrNull { it.length }!!.length + 1) / 4
    val stacks = (1..numberOfStacks).map { mutableListOf<Char>() }
    stacksInput.reversed().forEach { line ->
        line.chunked(4).forEachIndexed { index, chunk ->
            if (chunk[1] != ' ') {
                stacks[index].add(chunk[1])
            }
        }
    }

    val instructions = input
        .filter { it.startsWith("move") }
        .map{
            val parts = it.split(" ")
            val numberToMove = parts[1].toInt()
            val from = parts[3].toInt()
            val to = parts[5].toInt()
            Instruction(numberToMove, from, to)
        }

    println("Part 1: ${Day5.part1(stacks, instructions)}")
    println("Part 2: ${Day5.part2(stacks, instructions)}")
}

data class Instruction(val numberToMove:Int, val from:Int, val to:Int)

object Day5 {

    fun part1(stacksInput:List<MutableList<Char>>, instructions:List<Instruction>):String  {

        val stacks = stacksInput.map { it.toMutableList() }

        instructions.forEach {
            val charactersToMove = stacks[it.from - 1].takeLast(it.numberToMove)
            (1..it.numberToMove).forEach { _ -> stacks[it.from - 1].removeLast()}
            stacks[it.to - 1].addAll(charactersToMove.reversed())
        }

        return stacks.map { it.last().toString() }.reduce { acc, s -> acc+s }
    }

    fun part2(stacksInput:List<MutableList<Char>>, instructions:List<Instruction>):String  {

        val stacks = stacksInput.map { it.toMutableList() }

        instructions.forEach {
            val charactersToMove = stacks[it.from - 1].takeLast(it.numberToMove)
            (1..it.numberToMove).forEach { _ -> stacks[it.from - 1].removeLast()}
            stacks[it.to - 1].addAll(charactersToMove)
        }

        return stacks.map { it.last().toString() }.reduce { acc, s -> acc+s }
    }
}