package io.github.gunkim.question.application.service

import io.github.gunkim.question.application.port.`in`.FindQuestionsUseCase
import io.github.gunkim.question.application.port.out.LoadQuestionPort
import io.github.gunkim.question.domain.Question
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
open class FindQuestionsService(
    private val loadQuestionsPort: LoadQuestionPort
) : FindQuestionsUseCase {
    override fun findQuestions(): List<Question> = loadQuestionsPort.loadQuestion()
}