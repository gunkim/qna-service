package io.github.gunkim.question.application.port.out

import io.github.gunkim.question.domain.Answer

interface LoadAnswerPort {
    fun loadAnswer(answerId: Long): Answer
    fun loadAnswers(questionId: Long): List<Answer>
}