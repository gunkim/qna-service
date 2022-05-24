package io.github.gunkim.question.adapter.persistence

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@DisplayName("질문 레포지토리 테스트")
@AutoConfigureTestDatabase(replace = NONE)
open class QuestionJpaEntityRepositoryTests {
    @Autowired
    private lateinit var repository: QuestionJpaEntityRepository

    @Test
    fun persist() {
        val question = QuestionJpaEntity(
            categoryJpaEntity = CategoryJpaEntity(name = "자바"),
            tagJpaEntities = mutableSetOf(TagJpaEntity(name = "개발")),
            content = "안녕하세요",
            userEmbedded = UserEmbedded(
                username = "건김",
                password = "건김",
                ip = "127.0.0.1"
            )
        )
        val savedQuestion = repository.save(question)
        assertEquals(question, savedQuestion)
    }
}