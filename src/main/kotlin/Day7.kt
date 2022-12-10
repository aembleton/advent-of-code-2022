import java.io.File

fun main() {
    val input = File("src/main/resources/day7.txt").readLines()
    println("Part 1: ${Day7.part1(input)}")
    println("Part 2: ${Day7.part2(input)}")
}

data class Directory(val name:String, val size:Int = 0, val children:MutableList<Directory> = mutableListOf()) {
    fun totalSize():Int = size + children.sumOf { it.totalSize() }
    fun forPath(path:List<String>):Directory {
        if (path.isEmpty()) return this
        return children.find { it.name == path.first() }?.forPath(path.drop(1)) ?: this
    }
    fun allChildren():List<Directory> = children + children.flatMap { it.allChildren() }
}

object Day7 {

    fun part1(input:List<String>) = buildDirectoryStructure(input).allChildren()
            .filter { it.children.isNotEmpty() && it.totalSize() <= 100_000 }
            .sumOf { it.totalSize() }

    fun part2(input: List<String>): Int {
        val root = buildDirectoryStructure(input)
        val freeSpace = 70000000 - root.totalSize()
        val spaceNeeded = 30000000 - freeSpace
        return root.allChildren()
            .filter { it.children.isNotEmpty() && it.totalSize() > spaceNeeded }.minByOrNull { it.totalSize() }!!
            .totalSize()
    }

    private fun buildDirectoryStructure(input: List<String>): Directory {
        val root = Directory("root", 0)
        val path = mutableListOf<String>()

        input.drop(2).forEach { line ->
            val parts = line.split(" ")
            when {
                line.startsWith("$ cd ..") -> path.removeLast()
                line.startsWith("$ cd") -> {
                    val (_, _, dir) = parts
                    path.add(dir)
                }

                line.startsWith("$ ls") -> {}
                line.startsWith("dir") -> {
                    val (_, name) = parts
                    root.forPath(path).children.add(Directory(name))
                }

                else -> {
                    val (size, name) = parts
                    root.forPath(path).children.add(Directory(name, size.toInt()))
                }
            }
        }
        return root
    }


}