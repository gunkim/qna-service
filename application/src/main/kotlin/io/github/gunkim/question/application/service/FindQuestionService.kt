package io.github.gunkim.question.application.service

import io.github.gunkim.question.application.port.`in`.FindQuestionUseCase
import io.github.gunkim.question.application.port.out.LoadQuestionPort
import io.github.gunkim.question.domain.Question
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
open class FindQuestionService(
    private val loadQuestionsPort: LoadQuestionPort
) : FindQuestionUseCase {
    override fun findQuestions(): List<Question> = loadQuestionsPort.loadQuestion()

    override fun findQuestion(questionId: Long): Question = loadQuestionsPort.loadQuestion(questionId)
}