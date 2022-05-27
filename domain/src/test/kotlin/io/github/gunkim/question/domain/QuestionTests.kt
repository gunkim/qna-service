package io.github.gunkim.question.domain

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("질문 도메인 테스트")
class QuestionTests {
    @ParameterizedTest
    @MethodSource("notValidArguments")
    fun `내용이 2자 이하, 1500자 초과이면 인스턴스 생성에 실패한다`(content: String) {
        assertThrows<IllegalArgumentException>("답변 내용은 2자 이상 1500자 미만이어야 합니다.")
        {
            Question(
                category = Category(name = "테스트"),
                tags = mutableSetOf(Tag(name = "헬로로")),
                answers = mutableSetOf(),
                content = content,
                user = User("김건", "12341234", "127.0.0.1")
            )
        }
    }

    @ParameterizedTest
    @MethodSource("validArguments")
    fun `내용이 2자 이상 1500자 미만이면 인스턴스 생성에 성공한다`(content: String) {
        assertDoesNotThrow {
            Question(
                category = Category(name = "테스트"),
                tags = mutableSetOf(Tag(name = "헬로로")),
                answers = mutableSetOf(),
                content = content,
                user = User("김건", "12341234", "127.0.0.1")
            )
        }
    }

    companion object {
        @JvmStatic
        fun notValidArguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("1"),
                Arguments.of("1".repeat(1501))
            )
        }

        @JvmStatic
        fun validArguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("자바"),
                Arguments.of("1".repeat(1500))
            )
        }
    }
}