package com.project.translator.adapter.in.web.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExceptionHandlingIntegrationTest {
    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new DummyController())
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnNotFoundStatusForLanguage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/error/languageNotFound"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Language with id=<-8080> not found!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("NOT_FOUND"));
    }

    @Test
    void shouldReturnNotFoundStatusForMessage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/error/messageNotFound"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Message with id=<-19990> not found!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("NOT_FOUND"));
    }

    @Test
    void shouldReturnNotFoundStatusForTag() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/error/tagNotFound"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Tag with id=<-1> not found!"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("NOT_FOUND"));
    }

    @Test
    void shouldReturnBadRequestStatusBecauseOfOriginalMessageWhichIsNotNull() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/error/originalMessageNotNull"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Original message cannot contain original message id"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("BAD_REQUEST"));
    }

    @Test
    void shouldReturnBadRequestStatusForTranslationWhichCannotBeConverted() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/error/translationCannotBeConverted"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Message translation cannot be converted to original message"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("BAD_REQUEST"));
    }

    @Test
    void shouldReturnBadRequestStatusBecauseOfOriginalMessageWhichIsNotInEnglish() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/error/originalMessageNotInEnglish"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Language of message: Polish. " +
                        "Only messages in English can be original messages."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("BAD_REQUEST"));
    }
}
