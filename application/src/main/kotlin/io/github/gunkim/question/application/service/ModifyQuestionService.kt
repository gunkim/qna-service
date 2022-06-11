package io.github.gunkim.question.application.service

import io.github.gunkim.question.application.port.`in`.ModifyQuestionUseCase
import io.github.gunkim.question.application.port.out.LoadQuestionPort
import io.github.gunkim.question.application.port.out.ModifyQuestionPort
import io.github.gunkim.question.domain.Category
import io.github.gunkim.question.domain.Tag
import org.springframework.stereotype.Service

@Service
class ModifyQuestionService(
    private val loadQuestionPort: LoadQuestionPort,
    private val modifyQuestionPort: ModifyQuestionPort
) : ModifyQuestionUseCase {
    override fun modifyQuestion(
        questionId: Long,
        category: Category,
        tags: MutableSet<Tag>,
        content: String
    ) {
        val question = loadQuestionPort.loadQuestion(questionId)
            .change(category, tags, content)

        modifyQuestionPort.modifyQuestion(question)
    }
}