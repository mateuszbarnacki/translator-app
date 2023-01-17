package com.project.translator.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.translator.application.port.in.TagDetails;
import com.project.translator.application.port.in.TagUseCase;
import com.project.translator.domain.TagDomain;
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
class TagControllerTest {
    private MockMvc mvc;
    private ObjectMapper objectMapper;
    @MockBean
    private TagUseCase useCase;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new TagController(useCase)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldCreateTag() throws Exception {
        Object object = new Object() {
            private final String tag = "Good";
        };

        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonObject = objectMapper.writeValueAsString(object);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tags")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonObject);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(useCase).createTag(any(TagDetails.class));
    }

    @Test
    void shouldReturnBadRequestStatus() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/tags")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldReturnListOfTags() throws Exception {
        when(useCase.getTags())
                .thenReturn(List.of(TagDomain.builder().id(13L).tag("Beast").build()));

        mvc.perform(MockMvcRequestBuilders.get("/tags")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(13L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tag").value("Beast"));
    }

    @Test
    void shouldReturnSingleTag() throws Exception {
        when(useCase.getTagById(anyLong()))
                .thenReturn(TagDomain.builder().id(4L).tag("Best").build());

        mvc.perform(MockMvcRequestBuilders.get("/tags/{id}", 4L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tag").value("Best"));
    }

    @Test
    void shouldDeleteTag() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/tags/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(useCase).deleteTag(anyLong());
    }
}
