package io.github.gunkim.question.adapter.out.persistence.question

import io.github.gunkim.question.adapter.out.persistence.category.CategoryJpaEntity
import io.github.gunkim.question.adapter.out.persistence.tag.TagJpaEntity
import io.github.gunkim.question.application.port.out.LoadQuestionPort
import io.github.gunkim.question.application.port.out.ModifyQuestionPort
import io.github.gunkim.question.domain.Question
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QuestionPersistenceAdapter(
    private val questionRepository: QuestionJpaEntityRepository
) : LoadQuestionPort, ModifyQuestionPort {
    override fun loadQuestion(questionId: Long): Question {
        return questionRepository.findByIdOrNull(questionId)
            ?.let(QuestionJpaEntity::convertToDomain)
            ?: throw NotFoundException()
    }

    override fun loadQuestion(): List<Question> {
        return questionRepository.findAll()
            .map(QuestionJpaEntity::convertToDomain)
    }

    override fun modifyQuestion(question: Question) {
        val questionJpaEntity = questionRepository.findByIdOrNull(question.id)
            ?: error("해당 질문이 존재하지 않습니다. (question_id : ${question.id})")
        questionJpaEntity.update(
            categoryJpaEntity = question.category.let(::CategoryJpaEntity),
            tagJpaEntities = question.tags.map(::TagJpaEntity).toMutableSet(),
            content = question.content
        )
    }
}