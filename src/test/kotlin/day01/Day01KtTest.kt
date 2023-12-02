package day01

import kotlin.test.Test

class Day01KtTest {

    @Test
    fun main() {
        // test if implementation meets criteria from the description, like:
        val testInput = Utils.readInput("Day01_test.txt")
        check(Day01.part1(testInput) == 142)
    }
}