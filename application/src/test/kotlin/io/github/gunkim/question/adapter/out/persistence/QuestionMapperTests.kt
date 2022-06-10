package io.github.gunkim.question.adapter.out.persistence

import io.github.gunkim.question.adapter.out.persistence.category.CategoryJpaEntity
import io.github.gunkim.question.adapter.out.persistence.question.QuestionJpaEntity
import io.github.gunkim.question.adapter.out.persistence.question.QuestionMapper
import io.github.gunkim.question.adapter.out.persistence.tag.TagJpaEntity
import io.github.gunkim.question.adapter.out.persistence.user.UserEmbedded
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class QuestionMapperTests {

    @Test
    fun `엔티티 리스트, 도메인 리스트로 변환`() {
        val questionEntities: List<QuestionJpaEntity> = listOf(createQuestion())
        val question = sut.mapToDomainEntities(questionEntities)[0]
        val tag = question.tags.first()
        val category = question.category
        val user = question.user

        assertAll(
            { assertThat(question.id).isEqualTo(1L) },
            { assertThat(question.content).isEqualTo("안녕하세요") },
            { assertThat(question.createdAt).isEqualTo(now) },
            { assertThat(question.updatedAt).isEqualTo(now) },
            { assertThat(tag.id).isEqualTo(1L) },
            { assertThat(tag.name).isEqualTo("개발쓰") },
            { assertThat(tag.createdAt).isEqualTo(now) },
            { assertThat(tag.updatedAt).isEqualTo(now) },
            { assertThat(category.id).isEqualTo(1L) },
            { assertThat(category.name).isEqualTo("자바") },
            { assertThat(category.createdAt).isEqualTo(now) },
            { assertThat(category.updatedAt).isEqualTo(now) },
            { assertThat(user.username).isEqualTo("gunkim") },
            { assertThat(user.password).isEqualTo("12341234") },
            { assertThat(user.ip).isEqualTo("127.0.0.1") },
        )
    }

    private companion object {

        val sut = QuestionMapper()

        val now: LocalDateTime = LocalDate.of(2022, 6, 10).atStartOfDay()!!
    }

    private fun createQuestion(): QuestionJpaEntity = QuestionJpaEntity(
        id = 1L,
        categoryJpaEntity = CategoryJpaEntity(
            id = 1L,
            name = "자바",
            createdAt = now,
            updatedAt = now,
        ),
        tagJpaEntities = mutableSetOf(
            TagJpaEntity(
                id = 1L,
                name = "개발쓰",
                createdAt = now,
                updatedAt = now,
            )
        ),
        userEmbedded = UserEmbedded(
            username = "gunkim",
            password = "12341234",
            ip = "127.0.0.1"
        ),
        createdAt = now,
        updatedAt = now,
        content = "안녕하세요"
    )
}