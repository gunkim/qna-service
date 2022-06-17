package io.github.gunkim.question.adapter.out.persistence.answer

import io.github.gunkim.function.hashCodeOf
import io.github.gunkim.question.adapter.out.persistence.common.BaseTimeEntity
import io.github.gunkim.question.adapter.out.persistence.user.UserEmbedded
import io.github.gunkim.question.domain.Answer
import java.time.LocalDateTime
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "answer")
class AnswerJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var content: String,
    @Embedded
    val userEmbedded: UserEmbedded,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime? = null
) : BaseTimeEntity(createdAt, updatedAt) {
    fun convertToDomain(): Answer {
        return Answer(
            id = id,
            content = content,
            user = userEmbedded.convertToDomain(),
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    override fun equals(other: Any?): Boolean = this === other ||
        other is AnswerJpaEntity &&
        id == other.id &&
        content == other.content &&
        userEmbedded == other.userEmbedded &&
        createdAt == other.createdAt &&
        updatedAt == other.updatedAt

    override fun hashCode(): Int =
        hashCodeOf(
            id,
            content,
            userEmbedded,
            createdAt,
            updatedAt
        )
}