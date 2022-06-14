package io.github.gunkim.question.domain

import io.github.gunkim.function.hashCodeOf
import java.time.LocalDateTime

class Question private constructor(
    val id: Long? = null,
    val category: Category,
    val tags: MutableSet<Tag>,
    val answers: MutableSet<Answer>,
    val content: String,
    val user: User,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null
) {
    companion object {
        operator fun invoke(
            id: Long? = null,
            category: Category,
            tags: MutableSet<Tag>,
            answers: MutableSet<Answer>,
            content: String,
            user: User,
            createdAt: LocalDateTime = LocalDateTime.now(),
            updatedAt: LocalDateTime? = null
        ): Question {
            require(content.length in 2..1500) {
                "질문 내용은 2자 이상 1500자 미만이어야 합니다."
            }

            return Question(
                id = id,
                category = category,
                tags = tags,
                answers = answers,
                content = content,
                user = user,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }

    fun modify(category: Category, tags: MutableSet<Tag>, content: String): Question {
        return Question(
            id = id,
            category = category,
            tags = tags,
            content = content,
            answers = answers,
            user = user,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    fun isNotEmptyAnswer(): Boolean {
        return answers.isNotEmpty()
    }

    val tagNames: List<String> = tags.map(Tag::name)
    val categoryName = category.name

    override fun equals(other: Any?): Boolean = this === other ||
        other is Question &&
        id == other.id &&
        category == other.category &&
        tags == other.tags &&
        answers == other.answers &&
        content == other.content &&
        user == other.user &&
        createdAt == other.createdAt &&
        updatedAt == other.updatedAt

    override fun hashCode(): Int = hashCodeOf(id, category, tags, answers, content, user, createdAt, updatedAt)
}