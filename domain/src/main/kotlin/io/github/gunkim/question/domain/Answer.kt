package io.github.gunkim.question.domain

import io.github.gunkim.function.hashCodeOf
import java.time.LocalDateTime

class Answer private constructor(
    val id: Long?,
    val content: String,
    val user: User,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
) {
    companion object {
        operator fun invoke(
            id: Long? = null,
            content: String,
            user: User,
            createdAt: LocalDateTime = LocalDateTime.now(),
            updatedAt: LocalDateTime? = null
        ): Answer {
            return Answer(
                id = id,
                content = content.takeIf { it.length in 2..1_500 }
                    ?: throw IllegalArgumentException("답변 내용은 2자 이상 1500자 미만이어야 합니다."),
                user = user,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }

    override fun equals(other: Any?): Boolean = this === other ||
            other is Answer &&
            id == other.id &&
            content == other.content &&
            user == other.user &&
            createdAt == other.createdAt &&
            updatedAt == other.updatedAt

    override fun hashCode(): Int = hashCodeOf(id, content, user, createdAt, updatedAt)
}