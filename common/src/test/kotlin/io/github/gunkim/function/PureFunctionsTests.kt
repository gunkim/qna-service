package io.github.gunkim.function

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("퓨어 함수 테스트")
class PureFunctionsTests {
    @Test
    @DisplayName("넘어온 값에 기반하여 해시 값을 반환한다")
    fun hash() {
        val hashCode = hashCodeOf("Hello")
        assertEquals(hashCode, 69609650)
    }
}