package io.github.gunkim.question.adapter.out.persistence.question

import io.github.gunkim.question.application.port.out.LoadQuestionPort
import io.github.gunkim.question.domain.Question
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QuestionPersistenceAdapter(private val questionRepository: QuestionJpaEntityRepository) : LoadQuestionPort {
    override fun loadQuestion(questionId: Long): Question {
        return questionRepository.findByIdOrNull(questionId)
            ?.let(QuestionJpaEntity::convertToDomain)
            ?: throw NotFoundException()
    }

    override fun loadQuestion(): List<Question> {
        return questionRepository.findAll()
            .map(QuestionJpaEntity::convertToDomain)
    }
}