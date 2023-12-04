package day02

import println

object Day02 {
    fun part1(lines: List<String>): Int {
        return 0
    }

    fun part2(lines: List<String>): Int {
        return 0
    }

    fun main() {
        val day02Input = Utils.readInput("day02_input.txt")
        val part1Result = Day02.part1(day02Input)
        part1Result.println()

        val part2Result = Day02.part2(day02Input)
        part2Result.println()
    }
}

data class CubeSet(val blue: Int, val red: Int, val green: Int) {
    companion object {
        fun fromString(cubeSetString: String): CubeSet {
            val cubeStrings = cubeSetString.split(',').map { it.trim() }
            val blue: Int = extractColorValue(cubeStrings, "blue")
            val red: Int = extractColorValue(cubeStrings, "red")
            val green: Int = extractColorValue(cubeStrings, "green")

            return CubeSet(blue = blue, red = red, green = green)
        }

        private fun extractColorValue(cubeStrings: List<String>, color: String): Int {
            val colorString = cubeStrings.firstOrNull() {
                it.contains(other = color, ignoreCase = true)
            } ?: return 0
            return colorString
                .trim()
                .split(' ')[0]
                .toInt()
        }
    }
}
data class Game(val gameId: Int, val cubeSet: List<CubeSet>) {
    companion object {
        fun fromString(cubeSetString: String): Game {

            return Game(gameId = 0, cubeSet = emptyList())
        }
    }
}
data class Games(val games: Map<Int, Game>, val bagSetup: CubeSet)


