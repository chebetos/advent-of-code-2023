package day02

import kotlin.test.Test
import kotlin.test.assertEquals

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
}