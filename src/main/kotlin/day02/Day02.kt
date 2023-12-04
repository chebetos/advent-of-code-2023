package day02

import println
import java.util.LinkedList
import java.util.TreeMap

fun main() {
    val day02Input = Utils.readInput("day02_input.txt")

    val bagSetup = CubeSet(red = 12, green = 13, blue = 14)
    val part1Result = Day02.part1(bagSetup, day02Input)
    part1Result.println()

    val part2Result = Day02.part2(day02Input)
    part2Result.println()
}

object Day02 {
    fun part1(bagSetup: CubeSet, lines: List<String>): Int = Games
        .fromStringLines(bagSetup, lines)
        .sumOfValidGameIds()

    fun part2(lines: List<String>): Int {
        return 0
    }
}

data class CubeSet(val blue: Int = 0, val red: Int = 0, val green: Int = 0) {
    /**
     * Is not valid if any of the cube amount is higher than the amount in the bag setup
     */
    fun isValid(bagSetup: CubeSet): Boolean {
        if (this.blue > bagSetup.blue) {
            return false;
        }
        if (this.red > bagSetup.red) {
            return false;
        }
        if (this.green > bagSetup.green) {
            return false;
        }
        return true
    }

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
    fun isAPossibleGame(bagSetup: CubeSet): Boolean {
        //is a possible game if all the cube sets are valid
        return cubeSet.all { it.isValid(bagSetup) }
    }

    companion object {
        fun fromString(gameString: String): Game {
            val gameStringArray = gameString.split(':')
            val gameId = gameStringArray[0].removePrefix("Game ").toInt()

            val cubeSet: List<CubeSet> = gameStringArray[1]
                .split(';')
                .map { CubeSet.fromString(it) }

            return Game(gameId, cubeSet)
        }
    }
}
data class Games(val games: List<Game>, val bagSetup: CubeSet) {
    fun sumOfValidGameIds(): Int = games
        .filter { it.isAPossibleGame(this.bagSetup) }
        .sumOf { it.gameId }

    companion object {
        fun fromStringLines(bagSetup: CubeSet, gameStrings: List<String>): Games {
            val games: List<Game> = gameStrings.map { Game.fromString(it) }
            return Games(games, bagSetup)
        }
    }
}


