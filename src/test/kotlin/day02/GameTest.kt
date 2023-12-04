package day02

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class GameTest {
    @Test
    fun fromString() {
        val game = Game.fromString(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
        )
        assertEquals(
            Game(
                gameId = 1,
                cubeSets = listOf(
                    CubeSet(blue = 3, red = 4),
                    CubeSet(blue = 6, red = 1, green = 2),
                    CubeSet(green = 2),
                )
            ),
            game
        )
    }

    @Test
    fun isAPossibleGame() {
        val bagSetup = CubeSet(blue = 10, green = 10, red = 10)
        assertTrue(
            Game(
                gameId = 1,
                cubeSets = listOf(
                    CubeSet(blue = 3, red = 4),
                    CubeSet(blue = 6, red = 1, green = 2),
                    CubeSet(green = 2),
                )
            ).isAPossibleGame(bagSetup)
        )

        assertFalse(
            Game(
                gameId = 1,
                cubeSets = listOf(
                    CubeSet(blue = 3, red = 4),
                    CubeSet(blue = 6, red = 1, green = 2),
                    CubeSet(green = 20),
                )
            ).isAPossibleGame(bagSetup)
        )
    }
}