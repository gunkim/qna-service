package io.github.gunkim.question.adapter.out.persistence

import io.github.gunkim.question.application.port.out.LoadQuestionsPort
import io.github.gunkim.question.domain.Question
import org.springframework.stereotype.Component

@Component
class QuestionPersistenceAdapter(
    private val questionRepository: QuestionJpaEntityRepository,
    private val questionMapper: QuestionMapper
) : LoadQuestionsPort {
    override fun loadQuestions(): List<Question> {
        val questionEntities = questionRepository.findAll()
        return questionMapper.mapToDomainEntities(questionEntities)
    }
}