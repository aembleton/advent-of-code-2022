import java.io.File

fun main() {
    val input = File("src/main/resources/day6.txt").readText()
    println("Part 1: ${Day6.findMarker(input,4)}")
    println("Part 2: ${Day6.findMarker(input,14)}")
}

object Day6 {

    fun findMarker(dataStream:String, distinctChars:Int) = dataStream
        .windowed(distinctChars,1)
        .indexOfFirst { it.toSet().size == distinctChars } + distinctChars
}