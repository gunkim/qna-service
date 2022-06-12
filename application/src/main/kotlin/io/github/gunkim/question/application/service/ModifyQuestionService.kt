package io.github.gunkim.question.application.service

import io.github.gunkim.question.application.port.`in`.ModifyQuestionUseCase
import io.github.gunkim.question.application.port.out.LoadQuestionPort
import io.github.gunkim.question.application.port.out.ModifyQuestionPort
import io.github.gunkim.question.domain.Category
import io.github.gunkim.question.domain.Question
import io.github.gunkim.question.domain.Tag
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class ModifyQuestionService(
    private val loadQuestionPort: LoadQuestionPort,
    private val modifyQuestionPort: ModifyQuestionPort
) : ModifyQuestionUseCase {
    override fun modifyQuestion(
        questionId: Long,
        category: Category,
        tags: MutableSet<Tag>,
        content: String
    ) {
        val loadedQuestion: Question = loadQuestionPort.loadQuestion(questionId)
        val modifiedQuestion = loadedQuestion.modify(category, tags, content)
        modifyQuestionPort.modifyQuestion(modifiedQuestion)
    }
}