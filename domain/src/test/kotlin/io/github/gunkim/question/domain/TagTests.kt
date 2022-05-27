package io.github.gunkim.question.domain

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("태그 도메인 테스트")
class TagTests {
    @ParameterizedTest
    @ValueSource(strings = ["자", "자바", "자바자바자바자바자바자바자바자바자바자바자", "자바자바자바자바자바자바자바자바자바자바자바"])
    fun `이름이 3글자 미만, 20글자 초과할 경우 인스턴스 생성에 실패한다`(name: String) {
        assertThrows<IllegalArgumentException>("태그명은 3글자 이상 20글자 이하여야 합니다.")
        { Tag(name = name) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["자바자", "자바자바", "자바자바자바자바자바자바자바자바자바자", "자바자바자바자바자바자바자바자바자바자바"])
    fun `이름이 3글자 이상, 20글자 이하이면 인스턴스 생성에 성공한다`(name: String) {
        assertDoesNotThrow { Tag(name = name) }
    }
}