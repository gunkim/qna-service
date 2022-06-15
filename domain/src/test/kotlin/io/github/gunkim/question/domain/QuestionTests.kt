package io.github.gunkim.question.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
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
                answers = mutableListOf(),
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
                answers = mutableListOf(),
                content = content,
                user = User("김건", "12341234", "127.0.0.1")
            )
        }
    }

    @Test
    fun `질문 수정 테스트`() {
        val content = "수정된 질문이에요~"
        val category = Category(name = "안녕")
        val tags = mutableSetOf(Tag(name = "코틀린"))
        val question = createQuestion()

        val modifyQuestion = question.modify(category, tags, content)

        assertAll(
            { assertThat(modifyQuestion.id).isEqualTo(question.id) },
            { assertThat(modifyQuestion.content).isEqualTo(content) },
            { assertThat(modifyQuestion.category).isEqualTo(category) },
            { assertThat(modifyQuestion.tags).isEqualTo(tags) },
            { assertThat(modifyQuestion.answers).isEqualTo(question.answers) },
            { assertThat(modifyQuestion.user).isEqualTo(question.user) },
            { assertThat(modifyQuestion.createdAt).isEqualTo(question.createdAt) },
            { assertThat(modifyQuestion.updatedAt).isEqualTo(question.updatedAt) },
        )
    }

    companion object {
        fun createQuestion(): Question {
            return Question(
                id = 1L,
                category = Category(name = "테스트"),
                tags = mutableSetOf(Tag(name = "헬로로")),
                answers = mutableListOf(),
                content = "질문이에요~",
                user = User("김건", "12341234", "127.0.0.1")
            )
        }

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