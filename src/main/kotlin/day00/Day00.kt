package day00

import println

fun main() {
    val day01Input = Utils.readInput("day00_input.txt")
    val part1Result = Day01.part1(day01Input)
    part1Result.println()

    val part2Result = Day01.part2(day01Input)
    part2Result.println()
}

object Day01 {
    fun part1(lines: List<String>): Int {
        return lines.size
    }

    fun part2(lines: List<String>): Int {
        return lines.size
    }
}
