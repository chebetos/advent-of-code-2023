package day01

import println
import java.util.concurrent.atomic.AtomicInteger

object Day01 {
    fun part1(lines: List<String>): Int {
        val calibrationNumberByDocument: AtomicInteger = AtomicInteger(0)
        for (line in lines) {
            val calibrationNumber: Int = extractCalibrationNumber(line)

            calibrationNumberByDocument.addAndGet(calibrationNumber)
        }

        return calibrationNumberByDocument.get()
    }

    private fun extractCalibrationNumber(line: String): Int {
        val firstDigit: Char = line.toCharArray().first { it.isDigit() }
        val lastDigit: Char = line.toCharArray().last { it.isDigit() }
        return "$firstDigit$lastDigit".toInt()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }
}

fun main() {
    val day01Input = Utils.readInput("day01_input.txt")
    val part1Result = Day01.part1(day01Input)
    part1Result.println()
}
