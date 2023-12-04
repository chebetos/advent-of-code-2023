package day02

import println

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

    fun part2(lines: List<String>): Int = Games
        .fromStringLines(CubeSet(), lines)
        .sumOfPower()
}

data class CubeSet(val red: Int = 0, val blue: Int = 0, val green: Int = 0) {
    /**
     * Is not valid if any of the cube amount is higher than the amount in the bag setup
     */
    fun isValid(bagSetup: CubeSet): Boolean {
        if (this.red > bagSetup.red) {
            return false;
        }
        if (this.blue > bagSetup.blue) {
            return false;
        }
        if (this.green > bagSetup.green) {
            return false;
        }
        return true
    }

    fun power(): Int = (this.red * this.green * this.blue)

    companion object {
        fun fromString(cubeSetString: String): CubeSet {
            val cubeStrings = cubeSetString.split(',').map { it.trim() }
            val red: Int = extractColorValue(cubeStrings, "red")
            val blue: Int = extractColorValue(cubeStrings, "blue")
            val green: Int = extractColorValue(cubeStrings, "green")

            return CubeSet(red = red, blue = blue, green = green)
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
data class Game(val gameId: Int, val cubeSets: List<CubeSet>) {
    fun isAPossibleGame(bagSetup: CubeSet): Boolean {
        //is a possible game if all the cube sets are valid
        return cubeSets.all { it.isValid(bagSetup) }
    }

    fun minimumSetOfCubes() = CubeSet(
        red = cubeSets.maxOf { it.red },
        blue = cubeSets.maxOf { it.blue },
        green = cubeSets.maxOf { it.green },
    )

    fun power(): Int = this.minimumSetOfCubes().power()

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

    fun sumOfPower(): Int = games.sumOf { it.power() }

    companion object {
        fun fromStringLines(bagSetup: CubeSet, gameStrings: List<String>): Games {
            val games: List<Game> = gameStrings.map { Game.fromString(it) }
            return Games(games, bagSetup)
        }
    }
}


