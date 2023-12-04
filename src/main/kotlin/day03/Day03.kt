package day03

import println

fun main() {
    val day03Input = Utils.readInput("day03_input.txt")
    val part1Result = Day03.part1(day03Input)
    part1Result.println()

    val part2Result = Day03.part2(day03Input)
    part2Result.println()
}

object Day03 {
    fun part1(lines: List<String>): Int = lines.size

    fun part2(lines: List<String>): Int = lines.size
}


