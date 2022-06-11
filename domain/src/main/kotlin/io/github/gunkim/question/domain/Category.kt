package io.github.gunkim.question.domain

import io.github.gunkim.function.hashCodeOf
import java.time.LocalDateTime

class Category private constructor(
    val id: Long?,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
) {
    companion object {
        operator fun invoke(
            id: Long? = null,
            name: String,
            createdAt: LocalDateTime = LocalDateTime.now(),
            updatedAt: LocalDateTime? = null
        ): Category {
            require(name.length in 2..20) {
                "카테고리명은 2글자 이상 20 글자 이하여야 합니다."
            }

            return Category(
                id = id,
                name = name,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }

    override fun equals(other: Any?): Boolean = this === other ||
            other is Category &&
            id == other.id &&
            name == other.name &&
            createdAt == other.createdAt &&
            updatedAt == other.updatedAt

    override fun hashCode(): Int = hashCodeOf(id, name, createdAt, updatedAt)
}