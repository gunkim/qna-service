package io.github.gunkim.question.domain

import io.github.gunkim.function.hashCodeOf
import java.time.LocalDateTime

class Question(
    val id: Long? = null,
    val category: Category,
    val tags: MutableSet<Tag>,
    val answers: MutableList<Answer>,
    val content: String,
    val user: User,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = null
) {
    val tagNames: List<String> = tags.map(Tag::name)
    val categoryName = category.name

    init {
        require(content.length in 2..1500) {
            "질문 내용은 2자 이상 1500자 이하여야 합니다."
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

    fun isNotEmptyAnswer(): Boolean = answers.isNotEmpty()

    override fun equals(other: Any?): Boolean {
        return this === other ||
            other is Question &&
            id == other.id &&
            category == other.category &&
            tags == other.tags &&
            answers == other.answers &&
            content == other.content &&
            user == other.user &&
            createdAt == other.createdAt &&
            updatedAt == other.updatedAt
    }

    override fun hashCode(): Int = hashCodeOf(id, category, tags, answers, content, user, createdAt, updatedAt)
}