package io.github.gunkim

import io.github.gunkim.question.adapter.out.persistence.category.CategoryJpaEntity
import io.github.gunkim.question.adapter.out.persistence.question.QuestionJpaEntity
import io.github.gunkim.question.adapter.out.persistence.question.QuestionJpaEntityRepository
import io.github.gunkim.question.adapter.out.persistence.tag.TagJpaEntity
import io.github.gunkim.question.adapter.out.persistence.user.UserEmbedded
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataInitLoader(
    private val questionJpaEntityRepository: QuestionJpaEntityRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val entities = (1..30).map(::createQuestion)
        questionJpaEntityRepository.saveAll(entities)
    }

    private fun createQuestion(id: Int): QuestionJpaEntity = QuestionJpaEntity(
        content = "궁금한게 있어요!! $id",
        categoryJpaEntity = CategoryJpaEntity(name = "Java"),
        tagJpaEntities = mutableSetOf(TagJpaEntity(name = "궁금한거")),
        userEmbedded = UserEmbedded(
            username = "gunkim$id",
            password = "testtest$id",
            ip = "127.0.0.1"
        )
    )
}