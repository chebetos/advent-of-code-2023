package day02

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class CubeSetTest {
    @Test
    fun fromStringWithAllColors() {
        val cubeSet = CubeSet.fromString("1 green, 3 red, 6 blue")
        assertEquals(CubeSet(blue = 6, red = 3, green = 1), cubeSet)
    }

    @Test
    fun fromStringWithMissingColors() {
        assertEquals(
            CubeSet(blue = 1, green = 2, red = 0),
            CubeSet.fromString("1 blue, 2 green")
        )
        assertEquals(
            CubeSet(green = 2, red = 1, blue = 0),
            CubeSet.fromString("1 red, 2 green")
        )
        assertEquals(
            CubeSet(green = 3, blue = 0, red = 0),
            CubeSet.fromString("3 green")
        )
    }

    @Test
    fun fromStringWithNoColors() {
        val cubeSet = CubeSet.fromString("")
        assertEquals(CubeSet(blue = 0, green = 0, red = 0), cubeSet)
    }

    @Test
    fun isValid() {
        val bagSetup = CubeSet(blue = 10, green = 10, red = 10)
        assertTrue(
            CubeSet(blue = 1, green = 2, red = 0).isValid(bagSetup)
        )
        assertTrue(
            CubeSet(blue = 10, green = 10, red = 10).isValid(bagSetup)
        )

        assertFalse(
            CubeSet(blue = 1, green = 2, red = 20).isValid(bagSetup)
        )
        assertFalse(
            CubeSet(blue = 1, green = 20, red = 0).isValid(bagSetup)
        )
        assertFalse(
            CubeSet(blue = 20, green = 2, red = 0).isValid(bagSetup)
        )
        assertFalse(
            CubeSet(blue = 11, green = 11, red = 10).isValid(bagSetup)
        )
        assertFalse(
            CubeSet(blue = 10, green = 11, red = 11).isValid(bagSetup)
        )
        assertFalse(
            CubeSet(blue = 11, green = 11, red = 11).isValid(bagSetup)
        )
    }
}