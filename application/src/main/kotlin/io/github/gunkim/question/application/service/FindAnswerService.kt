package io.github.gunkim.question.application.service

import io.github.gunkim.question.application.port.`in`.FindAnswerUseCase
import io.github.gunkim.question.application.port.out.LoadAnswerPort
import io.github.gunkim.question.domain.Answer
import org.springframework.stereotype.Service

@Service
class FindAnswerService(
    private val loadAnswerPort: LoadAnswerPort
) : FindAnswerUseCase {
    override fun findAnswers(questionId: Long): List<Answer> {
        return loadAnswerPort.loadAnswers(questionId)
    }

    override fun findAnswer(answerId: Long): Answer {
        return loadAnswerPort.loadAnswer(answerId)
    }
}