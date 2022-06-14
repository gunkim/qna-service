package io.github.gunkim.question.application.service

import io.github.gunkim.question.application.port.`in`.DeleteQuestionUseCase
import io.github.gunkim.question.application.port.out.DeleteQuestionPort
import io.github.gunkim.question.application.port.out.LoadQuestionPort
import org.springframework.stereotype.Service

@Service
class DeleteQuestionService(
    private val loadQuestionPort: LoadQuestionPort,
    private val deleteQuestionPort: DeleteQuestionPort
) : DeleteQuestionUseCase {
    override fun deleteQuestion(questionId: Long) {
        val question = loadQuestionPort.loadQuestion(questionId)
        require(question.isNotEmptyAnswer()) {
            "답변이 있는 게시물은 삭제할 수 없습니다. (question_id : ${questionId})"
        }
        deleteQuestionPort.deleteQuestion(question.id!!)
    }
}