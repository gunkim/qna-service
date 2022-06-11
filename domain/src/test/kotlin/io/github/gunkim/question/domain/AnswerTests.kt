package io.github.gunkim.question.domain

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("답변 도메인 테스트")
class AnswerTests {
    @ParameterizedTest
    @MethodSource("notValidArguments")
    fun `내용이 2자 이하, 1500자 초과이면 인스턴스 생성에 실패한다`(content: String) {
        assertThrows<IllegalArgumentException>("답변 내용은 2자 이상 1500자 미만이어야 합니다.")
        {
            Answer(
                content = content,
                user = User("김건", "12341234", "127.0.0.1")
            )
        }
    }

    @ParameterizedTest
    @MethodSource("validArguments")
    fun `내용이 2자 이상 1500자 미만이면 인스턴스 생성에 성공한다`(content: String) {
        assertDoesNotThrow {
            Answer(
                content = content,
                user = User("김건", "12341234", "127.0.0.1")
            )
        }
    }

    companion object {
        @JvmStatic
        fun notValidArguments(): Stream<Arguments> {
            return Stream.of(
                arguments("1"),
                arguments("1".repeat(1_501))
            )
        }

        @JvmStatic
        fun validArguments(): Stream<Arguments> {
            return Stream.of(
                arguments("자바"),
                arguments("1".repeat(1_500))
            )
        }
    }
}