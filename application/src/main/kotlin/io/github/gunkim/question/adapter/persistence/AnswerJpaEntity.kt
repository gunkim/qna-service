package io.github.gunkim.question.adapter.persistence

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "answer")
class AnswerJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    val questionJpaEntity: QuestionJpaEntity,
    var content: String,
    @Embedded
    val userEmbedded: UserEmbedded,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime? = null
) : BaseTimeEntity(createdAt, updatedAt) {
    override fun equals(other: Any?): Boolean = this === other ||
            other is AnswerJpaEntity &&
            id == other.id &&
            questionJpaEntity == other.questionJpaEntity &&
            content == other.content &&
            userEmbedded == other.userEmbedded &&
            createdAt == other.createdAt &&
            updatedAt == other.updatedAt

    override fun hashCode(): Int =
        hashCodeOf(
            id,
            questionJpaEntity,
            content,
            userEmbedded,
            createdAt,
            updatedAt
        )
}