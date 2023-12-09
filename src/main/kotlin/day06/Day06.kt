package day06

import Utils
import println

fun main() {
    val day01Input = Utils.readInput("day06_input.txt")
    val part1Result = Day06.part1(day01Input)
    part1Result.println()

    val part2Result = Day06.part2(day01Input)
    part2Result.println()
}

object Day06 {
    fun part1(lines: List<String>): Int {
        val document = RacesDocument.fromString(lines)
        document.println()
        return document.calculateMarginOfError()
    }

    fun part2(lines: List<String>): Int {
        val document = RacesDocument.fromStringWithoutSpaces(lines)
        document.println()
        return document.calculateMarginOfError()
    }
}

private const val TIME_PREFFIX = "Time:"

private const val DISTANCE_PREFFIX = "Distance:"

data class RacesDocument(val races: List<Race>) {

    fun calculateMarginOfError(): Int {
        val marginOfError = races.map { it.calculateWaysToWin() }
            .reduce { acc, i -> acc * i }
        println("marginOfError: $marginOfError")
        return marginOfError
    }

    companion object {
        fun fromString(lines: List<String>): RacesDocument {
            val filteredLines = lines.filter { it.isNotBlank() }
            check(filteredLines.size == 2) { "Invalid input, it should be only 2 lines: $filteredLines"}
            check(filteredLines[0].startsWith(TIME_PREFFIX)) { "Invalid input, 1st line doesn't start with 'Time': ${filteredLines[0]}"}
            check(filteredLines[1].startsWith(DISTANCE_PREFFIX)) { "Invalid input, 2nd line doesn't start with 'Distance;: ${filteredLines[1]}"}

            val times = filteredLines[0].removePrefix(TIME_PREFFIX)
                .split(' ')
                .filter { it.isNotBlank() }
                .map { it.toLong() }
            val distances = filteredLines[1].removePrefix(DISTANCE_PREFFIX)
                .split(' ')
                .filter { it.isNotBlank() }
                .map { it.toLong() }

            check(times.size == distances.size) { "Invalid input not same amount of time records than distance records: $times vs $distances"}

            val races: MutableList<Race> = ArrayList()
            for (i in times.indices) {
                val race = Race(times[i], distances[i])
                races.add(race)
            }

            return RacesDocument(races)
        }

        fun fromStringWithoutSpaces(lines: List<String>): RacesDocument {
            val filteredLines = lines.filter { it.isNotBlank() }
            check(filteredLines.size == 2) { "Invalid input, it should be only 2 lines: $filteredLines"}
            check(filteredLines[0].startsWith(TIME_PREFFIX)) { "Invalid input, 1st line doesn't start with 'Time': ${filteredLines[0]}"}
            check(filteredLines[1].startsWith(DISTANCE_PREFFIX)) { "Invalid input, 2nd line doesn't start with 'Distance;: ${filteredLines[1]}"}

            val time = filteredLines[0].removePrefix(TIME_PREFFIX)
                .replace(" ", "")
                .toLong()
            val distance = filteredLines[1].removePrefix(DISTANCE_PREFFIX)
                .replace(" ", "")
                .toLong()

            return RacesDocument(listOf(Race(time, distance)))
        }
    }
}

data class Race(val time: Long, val distance: Long) {
    fun calculateAchievableDistances(): List<Long> {
        val achievableDistances: MutableList<Long> = ArrayList()
        for (i in 0..time) {
            val chargingTime = i
            val runningTime = time - i
            val velocity = chargingTime
            val distance = velocity * runningTime
            achievableDistances.add(distance)
        }
        return achievableDistances
    }

    fun calculateWaysToWin(): Int {
        val achievableDistances = calculateAchievableDistances()
        val waysToWin = achievableDistances.indices.filter { achievableDistances[it] > distance }
        //println("For race: $this,\n\tachievableDistances: $achievableDistances,\n\twaysToWin: ${waysToWin.size} - $waysToWin")
        return waysToWin.size
    }
}
