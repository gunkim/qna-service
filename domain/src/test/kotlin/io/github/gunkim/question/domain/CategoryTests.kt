package io.github.gunkim.question.domain

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("카테고리 도메인 테스트")
class CategoryTests {
    @ParameterizedTest
    @ValueSource(strings = ["밥", "자바자바자바자바자바자바자바자바자바자바자"])
    fun `이름이 2 글자 미만, 20글자 초과할 경우 인스턴스 생성에 실패한다`(name: String) {
        assertThrows<IllegalArgumentException>("카테고리명은 2글자 이상 20 글자 이하여야 합니다.")
        { Category(name = name) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["자바", "자바자바자바자바자바자바자바자바자바자바"])
    fun `이름이 2 글자 이상, 20글자 이하일 경우 인스턴스 생성에 성공한다`(name: String) {
        assertDoesNotThrow("카테고리명은 2글자 이상 20 글자 이하여야 합니다.")
        { Category(name = name) }
    }
}