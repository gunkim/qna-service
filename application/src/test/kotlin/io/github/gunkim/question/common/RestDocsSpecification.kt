package io.github.gunkim.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

@AutoConfigureRestDocs
@ExtendWith(
    RestDocumentationExtension::class
)
abstract class RestDocsSpecification {
    protected var objectMapper: ObjectMapper? = null
    private var contextProvider: RestDocumentationContextProvider? = null

    @BeforeEach
    private fun setUp(
        contextProvider: RestDocumentationContextProvider
    ) {
        this.objectMapper = ObjectMapper().registerModule(JavaTimeModule())
        this.contextProvider = contextProvider
    }

    protected fun mockMvc(controller: Any?): MockMvc {
        return MockMvcBuilders.standaloneSetup(controller)
            .setMessageConverters(jackson2HttpMessageConverter())
            .apply<StandaloneMockMvcBuilder>(documentationConfiguration(contextProvider))
            .build()
    }

    private fun jackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter {
        val converter = MappingJackson2HttpMessageConverter()
        converter.objectMapper = objectMapper
        return converter
    }

    companion object {
        protected fun documentRequest(): OperationRequestPreprocessor {
            return Preprocessors.preprocessRequest(prettyPrint())
        }

        protected fun documentResponse(): OperationResponsePreprocessor {
            return Preprocessors.preprocessResponse(prettyPrint())
        }
    }
}
