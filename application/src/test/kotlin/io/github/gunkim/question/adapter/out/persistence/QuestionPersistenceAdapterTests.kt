package io.github.gunkim.question.adapter.out.persistence

import io.github.gunkim.question.adapter.out.persistence.category.CategoryJpaEntity
import io.github.gunkim.question.adapter.out.persistence.question.QuestionJpaEntity
import io.github.gunkim.question.adapter.out.persistence.question.QuestionJpaEntityRepository
import io.github.gunkim.question.adapter.out.persistence.question.QuestionPersistenceAdapter
import io.github.gunkim.question.adapter.out.persistence.tag.TagJpaEntity
import io.github.gunkim.question.adapter.out.persistence.user.UserEmbedded
import io.github.gunkim.question.domain.Category
import io.github.gunkim.question.domain.Tag
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
open class QuestionPersistenceAdapterTests {
    @Autowired
    lateinit var repository: QuestionJpaEntityRepository

    lateinit var sut: QuestionPersistenceAdapter

    @BeforeEach
    fun setup() {
        sut = QuestionPersistenceAdapter(repository)
        repository.saveAndFlush(
            QuestionJpaEntity(
                categoryJpaEntity = CategoryJpaEntity(name = "자바"),
                tagJpaEntities = mutableSetOf(TagJpaEntity(name = "개발발")),
                content = "안녕하세요",
                userEmbedded = UserEmbedded(
                    username = "건김",
                    password = "12341234",
                    ip = "127.0.0.1"
                )
            )
        )
    }

    @Test
    fun `도메인 수정`() {
        val questionEntity = repository.findAll()[0]
        val updatedQuestion = questionEntity.convertToDomain().modify(
            category = Category(name = "스프링"),
            tags = mutableSetOf(Tag(name = "개발 팁")),
            content = "새로운 내용"
        )
        sut.modifyQuestion(updatedQuestion)

        assertAll(
            { assertThat(questionEntity.categoryJpaEntity.name).isEqualTo("스프링") },
            { assertThat(questionEntity.tagJpaEntities.size).isEqualTo(1) },
            { assertThat(questionEntity.tagJpaEntities.first().name).isEqualTo("개발 팁") },
            { assertThat(questionEntity.content).isEqualTo("새로운 내용") }
        )
    }

    @Test
    fun `도메인 삭제`() {
        val questionJpaEntity = repository.findAll()[0]
        sut.deleteQuestion(questionJpaEntity.id!!)

        val questionSize = repository.findAll().size
        assertThat(questionSize).isEqualTo(0)
    }
}