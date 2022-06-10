package io.github.gunkim.question.adapter.out.persistence.question

import io.github.gunkim.question.domain.Category
import io.github.gunkim.question.domain.Question
import io.github.gunkim.question.domain.Tag
import io.github.gunkim.question.domain.User
import org.springframework.stereotype.Component

@Component
class QuestionMapper {
    fun mapToDomainEntities(questionJpaEntities: List<QuestionJpaEntity>): List<Question> {
        return questionJpaEntities
            .asSequence()
            .map(::mapToDomainEntity)
            .toList()
    }

    private fun mapToDomainEntity(questionJpaEntity: QuestionJpaEntity): Question {
        val categoryJpaEntity = questionJpaEntity.categoryJpaEntity
        val tagJpaEntities = questionJpaEntity.tagJpaEntities
        val userEmbedded = questionJpaEntity.userEmbedded
        return Question(
            id = questionJpaEntity.id,
            content = questionJpaEntity.content,
            createdAt = questionJpaEntity.createdAt,
            updatedAt = questionJpaEntity.updatedAt,
            category = Category(
                id = categoryJpaEntity.id,
                name = categoryJpaEntity.name,
                createdAt = categoryJpaEntity.createdAt,
                updatedAt = categoryJpaEntity.updatedAt
            ),
            tags = tagJpaEntities.asSequence()
                .map {
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