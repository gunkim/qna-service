package io.github.gunkim.question.adapter.out.persistence.question

import io.github.gunkim.function.hashCodeOf
import io.github.gunkim.question.adapter.out.persistence.category.CategoryJpaEntity
import io.github.gunkim.question.adapter.out.persistence.common.BaseTimeEntity
import io.github.gunkim.question.adapter.out.persistence.tag.TagJpaEntity
import io.github.gunkim.question.adapter.out.persistence.user.UserEmbedded
import io.github.gunkim.question.domain.Category
import io.github.gunkim.question.domain.Question
import io.github.gunkim.question.domain.Tag
import io.github.gunkim.question.domain.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "question")
class QuestionJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @OneToOne(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.PERSIST, CascadeType.REMOVE],
        orphanRemoval = true
    )
    val categoryJpaEntity: CategoryJpaEntity,
    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.REMOVE],
        orphanRemoval = true
    )
    val tagJpaEntities: MutableSet<TagJpaEntity> = mutableSetOf(),
    var content: String,
    @Embedded
    val userEmbedded: UserEmbedded,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime? = null
) : BaseTimeEntity(createdAt, updatedAt) {
    override fun equals(other: Any?): Boolean = this === other ||
            other is QuestionJpaEntity &&
            id == other.id &&
            categoryJpaEntity == other.categoryJpaEntity &&
            tagJpaEntities == other.tagJpaEntities &&
            content == other.content &&
            userEmbedded == other.userEmbedded &&
            createdAt == other.createdAt &&
            updatedAt == other.updatedAt

    override fun hashCode(): Int =
        hashCodeOf(
            id,
            categoryJpaEntity,
            tagJpaEntities,
            content,
            userEmbedded,
            createdAt,
            updatedAt
        )

    fun convertToDomain(): Question {
        val categoryJpaEntity = categoryJpaEntity
        val tagJpaEntities = tagJpaEntities
        val userEmbedded = userEmbedded
        return Question(
            id = id,
            content = content,
            createdAt = createdAt,
            updatedAt = updatedAt,
            category = Category(
                id = categoryJpaEntity.id,
                name = categoryJpaEntity.name,
                createdAt = categoryJpaEntity.createdAt,
                updatedAt = categoryJpaEntity.updatedAt
            ),
            tags = tagJpaEntities.map {
                Tag(
                    id = it.id,
                    name = it.name,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt
                )
            }.toMutableSet(),
            answers = mutableSetOf(),
            user = User(
                username = userEmbedded.username,
                password = userEmbedded.password,
                ip = userEmbedded.ip
            )
        )
    }
}

