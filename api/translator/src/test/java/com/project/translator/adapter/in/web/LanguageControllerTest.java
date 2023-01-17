package com.project.translator.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.translator.application.port.in.LanguageDetails;
import com.project.translator.application.port.in.LanguageUseCase;
import com.project.translator.domain.LanguageDomain;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LanguageControllerTest {
    private MockMvc mvc;
    private ObjectMapper objectMapper;
    @MockBean
    private LanguageUseCase useCase;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new LanguageController(useCase)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldCreateLanguage() throws Exception {
        Object object = new Object() {
            private final String language = "Polish";
        };

        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonObject = objectMapper.writeValueAsString(object);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/languages")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonObject);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(useCase).createLanguage(any(LanguageDetails.class));
    }

    @Test
    void shouldReturnBadRequestStatus() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/languages")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldReturnListOfLanguages() throws Exception {
        when(useCase.getLanguages())
                .thenReturn(List.of(LanguageDomain.builder().id(2L).language("Polish").build()));

        mvc.perform(MockMvcRequestBuilders.get("/languages")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].language").value("Polish"));
    }

    @Test
    void shouldReturnSingleLanguage() throws Exception {
        when(useCase.getLanguageById(anyLong()))
                .thenReturn(LanguageDomain.builder().id(1L).language("English").build());

        mvc.perform(MockMvcRequestBuilders.get("/languages/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.language").value("English"));
    }

    @Test
    void shouldDeleteLanguage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/languages/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(useCase).deleteLanguage(anyLong());
    }
}
