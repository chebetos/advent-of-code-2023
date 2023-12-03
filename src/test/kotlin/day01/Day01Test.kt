package day01

import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    @Test
    fun day01Part1() {
        // test if implementation meets criteria from the description, like:
        val testInput = Utils.readInput("Day01_part1_test.input")
        assertEquals(142, Day01.part1(testInput))
    }

    @Test
    fun day01Part2_extractCalibrationNumberWithDigitsInLetters() {
        // test if implementation meets criteria from the description, like:
        assertEquals(42, Day01.extractCalibrationNumberWithDigitsInLetters("4nineeightseven2"))
    }

    @Test
    fun day01Part2() {
        // test if implementation meets criteria from the description, like:
        val testInput = Utils.readInput("Day01_part2_test.input")
        assertEquals(281, Day01.part2(testInput))
    }
}