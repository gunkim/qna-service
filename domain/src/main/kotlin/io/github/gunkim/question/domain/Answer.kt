package io.github.gunkim.question.domain

import io.github.gunkim.function.hashCodeOf
import java.time.LocalDateTime

class Answer(
    val id: Long? = null,
    val content: String,
    val user: User,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = null
) {
    init {
        require(content.length in 2..1_500) {
            "답변 내용은 2자 이상 1500자 이하여야 합니다."
        }
    }

    override fun equals(other: Any?): Boolean {
        return this === other ||
            other is Answer &&
            id == other.id &&
            content == other.content &&
            user == other.user &&
            createdAt == other.createdAt &&
            updatedAt == other.updatedAt
    }

    override fun hashCode(): Int = hashCodeOf(id, content, user, createdAt, updatedAt)
}