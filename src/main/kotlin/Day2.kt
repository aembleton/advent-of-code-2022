import java.io.File

fun main() {
    val input = File("src/main/resources/day2.txt").readLines()
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(input: List<String>) = input.sumOf { line ->
    val parts = line.split(" ").map { it.toCharArray().first() }
    val myChoice = myChoice(parts[1])
    val outcomeOfRound = outcomeOfRound(parts[0], parts[1])
    val result = myChoice + outcomeOfRound
//    println("$line: $myChoice + $outcomeOfRound = $result")
    result
}

fun part2(input: List<String>) = input.sumOf { line ->
    val parts = line.split(" ").map { it.toCharArray().first() }
    val opponent = parts[0]
    val instruction = parts[1]
    when(opponent) {
        'A' -> when(instruction) {
            'X' -> 0 + 3
            'Y' -> 3 + 1
            'Z' -> 6 + 2
            else -> throw IllegalArgumentException("Invalid result: $instruction")
        }
        'B' -> when(instruction) {
            'X' -> 0 + 1
            'Y' -> 3+ 2
            'Z' -> 6 +3
            else -> throw IllegalArgumentException("Invalid result: $instruction")
        }
        'C' -> when(instruction) {
            'X' -> 0+ 2
            'Y' -> 3 + 3
            'Z' -> 6+ 1
            else -> throw IllegalArgumentException("Invalid result: $instruction")
        }
        else -> throw IllegalArgumentException("Invalid opponent: $opponent")
    }.toInt()
}


fun myChoice(input:Char) = when(input) {
    'X' -> 1
    'Y' -> 2
    'Z' -> 3
    else -> 0
}

fun outcomeOfRound(player1:Char, player2:Char)= when(player1) {
    'A' -> when(player2) {
        'X' -> 3
        'Y' -> 6
        'Z' -> 0
        else -> 0
    }
    'B' -> when(player2) {
        'X' -> 0
        'Y' -> 3
        'Z' -> 6
        else -> 0
    }
    'C' -> when(player2) {
        'X' -> 6
        'Y' -> 0
        'Z' -> 3
        else -> 0
    }
    else -> 0
}