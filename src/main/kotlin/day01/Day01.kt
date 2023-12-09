package day01

import Utils
import println
import java.util.concurrent.atomic.AtomicInteger

object Day01 {
    fun part1(lines: List<String>): Int {
        val calibrationNumberByDocument = AtomicInteger(0)
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

    fun part2(lines: List<String>): Int {
        val calibrationNumberByDocument = AtomicInteger(0)
        for (line in lines) {
            val calibrationNumber: Int = extractCalibrationNumberWithDigitsInLetters(line)
            println("For line: $line - calibration number: $calibrationNumber" )
            calibrationNumberByDocument.addAndGet(calibrationNumber)
        }

        println("Total: $calibrationNumberByDocument" )

        return calibrationNumberByDocument.get()
    }

    fun extractCalibrationNumberWithDigitsInLetters(line: String): Int {

        var firstDigit: Int? = line.toCharArray().firstOrNull() { it.isDigit() }?.digitToInt()
        var lastDigit: Int? = line.toCharArray().lastOrNull() { it.isDigit() }?.digitToInt()

        var firstDigitInLetters: Pair<DigitsInLetters, Int> = if (null == firstDigit) {
            Pair(DigitsInLetters.zero, Int.MAX_VALUE)
        } else {
            Pair(DigitsInLetters.fromInt(firstDigit)!!, line.indexOf(firstDigit.digitToChar()))
        }

        var lastDigitInLetters: Pair<DigitsInLetters, Int> = if (null == lastDigit) {
            Pair(DigitsInLetters.zero, Int.MIN_VALUE)
        } else {
            Pair(DigitsInLetters.fromInt(lastDigit)!!, line.lastIndexOf(lastDigit.digitToChar()))
        }

        for (digitEntry in DigitsInLetters.entries) {
            var idxByLetter = line.indexOf(string = digitEntry.name, ignoreCase = true)
            var idxByDigit = line.indexOf(string = digitEntry.intValue.toString())

            if (idxByLetter < 0) {
                idxByLetter = Int.MAX_VALUE
            }

            if (idxByDigit < 0) {
                idxByDigit = Int.MAX_VALUE
            }

            val firstIdx = idxByLetter.coerceAtMost(idxByDigit)
            if (firstIdx < firstDigitInLetters.second) {
                firstDigitInLetters = Pair(digitEntry, firstIdx)
            }

            val lastIdxByLetter = line
                .lastIndexOf(string = digitEntry.name, ignoreCase = true)
            val lastIdxByDigit = line
                .lastIndexOf(string = digitEntry.intValue.toString())

            val lastIdx = lastIdxByLetter.coerceAtLeast(lastIdxByDigit)

            if (lastIdx > lastDigitInLetters.second) {
                lastDigitInLetters = Pair(digitEntry, lastIdx)
            }
        }

        return "${firstDigitInLetters.first.intValue}${lastDigitInLetters.first.intValue}".toInt()
    }

    enum class DigitsInLetters(val intValue: Int) {
        zero(0),
        one(1),
        two(2),
        three(3),
        four(4),
        five(5),
        six(6),
        seven(7),
        eight(8),
        nine(9);

        companion object {
            fun fromInt(n: Int?): DigitsInLetters? {
                return entries.find { d -> d.intValue == n }
            }
        }
    }
}



fun main() {
    val day01Input = Utils.readInput("day01_input.txt")
    val part1Result = Day01.part1(day01Input)
    part1Result.println()

    val part2Result = Day01.part2(day01Input)
    part2Result.println()
}
