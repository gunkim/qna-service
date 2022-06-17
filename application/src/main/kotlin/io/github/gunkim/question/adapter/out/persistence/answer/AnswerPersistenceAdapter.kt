package io.github.gunkim.question.adapter.out.persistence.answer

import io.github.gunkim.question.adapter.out.persistence.question.QuestionJpaEntityRepository
import io.github.gunkim.question.application.port.out.LoadAnswerPort
import io.github.gunkim.question.domain.Answer
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class AnswerPersistenceAdapter(
    private val questionRepository: QuestionJpaEntityRepository,
    private val answerRepository: AnswerJpaEntityRepository
) : LoadAnswerPort {
    override fun loadAnswer(answerId: Long): Answer {
        return answerRepository.findByIdOrNull(answerId)?.convertToDomain()
            ?: error("해당 답변이 존재하지 않습니다. (answer_id : ${answerId}")
    }

    override fun loadAnswers(questionId: Long): List<Answer> {
        val question = questionRepository.findByIdOrNull(questionId)
            ?: error("해당 답변이 존재하지 않습니다. (question_id : ${questionId}")

        return question.answerEntities
            .map(AnswerJpaEntity::convertToDomain)
    }
}