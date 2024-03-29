package com.project.translator.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.translator.application.port.in.MessageDetails;
import com.project.translator.application.port.in.MessageUseCase;
import com.project.translator.application.port.out.MessageDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageControllerTest {
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MessageUseCase useCase;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new MessageController(useCase)).build();
    }

    @Test
    void shouldCreateMessage() throws Exception {
        Object object = new Object() {
            private final Long original_message = 4L;
            private final Long language = 1L;
            private final String content = "Test";
            private final List<Long> tags = Collections.emptyList();
        };

        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonObject = objectMapper.writeValueAsString(object);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/messages")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonObject);

        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(useCase).createMessage(any(MessageDetails.class));
    }

    @Test
    void shouldReturnBadRequestStatus() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/messages")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldReturnListOfMessages() throws Exception {
        when(useCase.getMessages())
                .thenReturn(buildGetMessagesResult());

        mvc.perform(MockMvcRequestBuilders.get("/messages")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].language").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("Lorem ipsum"));
    }

    @Test
    void shouldReturnSingleMessage() throws Exception {
        when(useCase.getMessageById(anyLong()))
                .thenReturn(buildGetMessageResult());

        mvc.perform(MockMvcRequestBuilders.get("/messages/{id}", 12L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(12L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.originalMessage").value(3L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.language").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Test"));
    }

    @Test
    void shouldDeleteMessage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/messages/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(useCase).deleteMessage(anyLong());
    }

    @Test
    void shouldFindMessagesByLanguage() throws Exception {
        // given
        List<MessageDto> expectedMessages = buildGetMessagesResult();
        when(useCase.findMessagesByLanguage(anyString())).thenReturn(expectedMessages);
        // when
        MvcResult result = mvc.perform(
                        MockMvcRequestBuilders.get("/messages/language").param("value", "testLanguage")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        // then
        List<MessageDto> actual = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<>() {
                });
        assertThat(actual).containsExactlyElementsOf(expectedMessages);
    }


    @Test
    void shouldFindMessagesByTag() throws Exception {
        // given
        List<MessageDto> expectedMessages = buildGetMessagesResult();
        when(useCase.findMessagesByTag(anyString())).thenReturn(expectedMessages);
        // when
        MvcResult result = mvc.perform(
                        MockMvcRequestBuilders.get("/messages/tag").param("value", "testTag")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        // then
        List<MessageDto> actual = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<>() {
                });
        assertThat(actual).containsExactlyElementsOf(expectedMessages);
    }

    private List<MessageDto> buildGetMessagesResult() {
        return List.of(MessageDto.builder()
                .id(2L)
                .originalMessage(1L)
                .language(1L)
                .content("Lorem ipsum")
                .tags(buildTagDomain()).build());
    }

    private MessageDto buildGetMessageResult() {
        return MessageDto.builder()
                .id(12L)
                .originalMessage(3L)
                .language(4L)
                .content("Test")
                .tags(buildTagDomain())
                .build();
    }

    private Set<Long> buildTagDomain() {
        return Set.of(3L);
    }
}
