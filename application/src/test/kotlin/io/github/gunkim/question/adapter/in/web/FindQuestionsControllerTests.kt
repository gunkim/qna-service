package io.github.gunkim.question.adapter.`in`.web

import io.github.gunkim.common.RestDocsSpecification
import io.github.gunkim.question.adapter.`in`.web.docs.FindQuestionsDocumentation
import io.github.gunkim.question.application.port.`in`.FindQuestionUseCase
import io.github.gunkim.question.domain.Category
import io.github.gunkim.question.domain.Question
import io.github.gunkim.question.domain.Tag
import io.github.gunkim.question.domain.User
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@DisplayName("질문 목록 조회 docs 테스트")
class FindQuestionsControllerTests : RestDocsSpecification() {

    @Test
    fun `질문 목록 조회`() {
        val usecase = FakeFindQuestionUseCase()
        mockMvc(FindQuestionsController(usecase))
            .perform(get("/api/v1/questions"))
            .andExpect(status().isOk)
            .andDo(print())
            .andDo(FindQuestionsDocumentation.findQuestions())
    }

    companion object {
        val fakeQuestion = Question(
            id = 1L,
            content = "궁금한게 있어요!!",
            category = Category(name = "Java"),
            user = User(username = "gunkim", password = "testtest", ip = "127.0.0.1"),
            tags = mutableSetOf(Tag(name = "궁금한거")),
            answers = mutableListOf(),
            createdAt = LocalDate.of(2022, 3, 31).atTime(15, 37)
        )

        class FakeFindQuestionUseCase : FindQuestionUseCase {
            override fun findQuestions(): List<Question> = listOf(fakeQuestion)

            override fun findQuestion(questionId: Long): Question {
                TODO("Not yet implemented")
            }
        }
    }
}