package io.github.gunkim.question.application.service

import io.github.gunkim.question.application.port.out.LoadQuestionPort
import io.github.gunkim.question.domain.Category
import io.github.gunkim.question.domain.Question
import io.github.gunkim.question.domain.Tag
import io.github.gunkim.question.domain.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("게시글 삭제 서비스 테스트")
class DeleteQuestionServiceTests {
    lateinit var sut: DeleteQuestionService

    @BeforeEach
    fun setup() {
        sut = DeleteQuestionService(
            FakeLoadQuestionPort()
        ) {}
    }

    @Test
    fun `답변이 존재한 게시글을 삭제하면 예외가 발생한다`() {
        val questionId = 1L
        assertThrows<IllegalArgumentException>("답변이 있는 게시글은 삭제할 수 없습니다. (question_id : ${questionId})")
        { sut.deleteQuestion(questionId) }
    }

    companion object {
        class FakeLoadQuestionPort : LoadQuestionPort {
            override fun loadQuestion(questionId: Long): Question {
                return Question(
                    id = 1L,
                    category = Category(name = "테스트"),
                    tags = mutableSetOf(Tag(name = "헬로로")),
                    answers = mutableSetOf(),
                    content = "질문이에요~",
                    user = User("김건", "12341234", "127.0.0.1")
                )
            }

            override fun loadQuestion(): List<Question> {
                TODO("Not yet implemented")
            }
        }
    }
}