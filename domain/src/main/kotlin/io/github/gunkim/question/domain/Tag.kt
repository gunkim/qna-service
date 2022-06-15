package io.github.gunkim.question.domain

import io.github.gunkim.function.hashCodeOf
import java.time.LocalDateTime

class Tag(
    val id: Long? = null,
    val name: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = null
) {
    init {
        require(name.length in 3..20) {
            "태그명은 3글자 이상 20글자 이하여야 합니다."
        }
    }

    override fun equals(other: Any?): Boolean {
        return this === other ||
            other is Tag &&
            id == other.id &&
            name == other.name &&
            createdAt == other.createdAt &&
            updatedAt == other.updatedAt
    }

    override fun hashCode(): Int = hashCodeOf(id, name, createdAt, updatedAt)
}