package io.github.gunkim.question.domain

import io.github.gunkim.function.hashCodeOf
import java.time.LocalDateTime

class Tag private constructor(
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
        ): Tag {
            return Tag(
                id = id,
                name = name.takeIf { it.length in 3..20 }
                    ?: throw IllegalArgumentException("태그명은 3글자 이상 20글자 미만이어야 합니다."),
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }

    override fun equals(other: Any?): Boolean = this === other ||
            other is Tag &&
            id == other.id &&
            name == other.name &&
            createdAt == other.createdAt &&
            updatedAt == other.updatedAt

    override fun hashCode(): Int = hashCodeOf(id, name, createdAt, updatedAt)
}