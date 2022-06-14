package io.github.gunkim.question.application.port.out

fun interface DeleteQuestionPort {
    fun deleteQuestion(questionId: Long)
}