package io.github.gunkim.question.adapter.persistence

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@DisplayName("답변 레포지토리 테스트")
@AutoConfigureTestDatabase(replace = NONE)
open class AnswerJpaEntityRepositoryTests {
    @Autowired
    private lateinit var answerRepository: AnswerJpaEntityRepository

    @Autowired
    private lateinit var questionRepository: QuestionJpaEntityRepository

    @Test
    fun persist() {
        val question = questionRepository.save(
            QuestionJpaEntity(
                categoryJpaEntity = CategoryJpaEntity(name = "자바"),
                tagJpaEntities = mutableSetOf(TagJpaEntity(name = "개발")),
                content = "안녕하세요",
                userEmbedded = UserEmbedded(
                    username = "건김",
                    password = "건김",
                    ip = "127.0.0.1"
                )
            )
        )
        val answer = AnswerJpaEntity(
            questionJpaEntity = question,
            content = "답변입니다.",
            userEmbedded = UserEmbedded(
                username = "태양신",
                password = "12341234",
                ip = "127.0.0.1"
            )
        )
        val savedAnswer = answerRepository.save(answer)
        assertEquals(answer, savedAnswer)
    }
}