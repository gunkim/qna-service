package io.github.gunkim.question.application.service

import io.github.gunkim.question.application.port.`in`.FindQuestionsUseCase
import io.github.gunkim.question.application.port.out.LoadQuestionsPort
import io.github.gunkim.question.domain.Question
import org.springframework.stereotype.Service

@Service
class FindQuestionsService(
    private val loadQuestionsPort: LoadQuestionsPort
) : FindQuestionsUseCase {
    override fun findQuestions(): List<Question> = loadQuestionsPort.loadQuestions()
}