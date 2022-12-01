import java.io.File

fun main() {
    val reindeer = mutableListOf<Int>()
    var newReindeer = true
    File("src/main/resources/day1.txt").readLines().forEach {
        if (it.isBlank()) {
            newReindeer = true
        } else {
            val calories = it.toInt()
            if (newReindeer) {
                reindeer.add(calories)
                newReindeer = false
            } else {
                reindeer[reindeer.size - 1] += calories
            }
        }
    }

    val part1 = reindeer.maxOrNull() ?: 0
    println("Part 1: $part1")

    val part2 = reindeer.sortedDescending().take(3).sum()
    println("Part 2: $part2")
}