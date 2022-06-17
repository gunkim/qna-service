package io.github.gunkim.question.adapter.out.persistence

import io.github.gunkim.question.adapter.out.persistence.answer.AnswerJpaEntity
import io.github.gunkim.question.adapter.out.persistence.answer.AnswerJpaEntityRepository
import io.github.gunkim.question.adapter.out.persistence.answer.AnswerPersistenceAdapter
import io.github.gunkim.question.adapter.out.persistence.category.CategoryJpaEntity
import io.github.gunkim.question.adapter.out.persistence.question.QuestionJpaEntity
import io.github.gunkim.question.adapter.out.persistence.question.QuestionJpaEntityRepository
import io.github.gunkim.question.adapter.out.persistence.tag.TagJpaEntity
import io.github.gunkim.question.adapter.out.persistence.user.UserEmbedded
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
open class AnswerPersistenceAdapterTests {
    @Autowired
    lateinit var questionRepository: QuestionJpaEntityRepository

    @Autowired
    lateinit var answerRepository: AnswerJpaEntityRepository

    lateinit var sut: AnswerPersistenceAdapter

    @BeforeEach
    fun setup() {
        sut = AnswerPersistenceAdapter(questionRepository, answerRepository)
        questionRepository.saveAndFlush(
            QuestionJpaEntity(
                categoryJpaEntity = CategoryJpaEntity(name = "자바"),
                tagJpaEntities = mutableSetOf(TagJpaEntity(name = "개발발")),
                answerEntities = mutableListOf(
                    AnswerJpaEntity(
                        content = "잘하시네요",
                        userEmbedded = UserEmbedded(
                            username = "킹왕짱맨",
                            password = "12341234",
                            ip = "127.0.0.1"
                        )
                    )
                ),
                content = "안녕하세요",
                userEmbedded = UserEmbedded(
                    username = "건김",
                    password = "12341234",
                    ip = "127.0.0.1"
                )
            )
        )
    }
}