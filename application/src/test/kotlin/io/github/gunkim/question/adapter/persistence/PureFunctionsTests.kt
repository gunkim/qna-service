package io.github.gunkim.question.adapter.persistence

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PureFunctionsTests {
    @Test
    fun hash() {
        val hashCode = hashCodeOf("Hello")
        assertEquals(hashCode, 69609650)
    }
}