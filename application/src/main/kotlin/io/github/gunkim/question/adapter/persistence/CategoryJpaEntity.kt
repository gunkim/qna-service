package io.github.gunkim.question.adapter.persistence

import io.github.gunkim.function.hashCodeOf
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "category")
class CategoryJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime? = null
) : BaseTimeEntity(createdAt, updatedAt) {
    override fun equals(other: Any?): Boolean = this === other ||
            other is CategoryJpaEntity &&
            id == other.id &&
            name == other.name &&
            createdAt == other.createdAt &&
            updatedAt == other.updatedAt

    override fun hashCode(): Int = hashCodeOf(id, name, createdAt, updatedAt)
}