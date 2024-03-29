package com.project.translator.adapter.in.web;

import com.project.translator.application.port.in.MessageDetails;
import com.project.translator.application.port.in.MessageUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.project.translator.application.port.out.MessageDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@Tag(name = "Messages")
@RequiredArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageUseCase messageUseCase;

    @Operation(summary = "Returns all messages")
    @GetMapping
    Collection<MessageDto> getMessages() {
        return messageUseCase.getMessages();
    }


    @Operation(summary = "Returns message by id")

    @GetMapping(value = "/{id}")
    MessageDto getById(@PathVariable("id") Long id) {
        return messageUseCase.getMessageById(id);
    }

    @Operation(summary = "Returns message written in given language")
    @GetMapping(value = "/language")
    List<MessageDto> findMessagesByLanguage(@RequestParam("value") @NotNull(message = "missing language param") String language) {
        return messageUseCase.findMessagesByLanguage(language);
    }

    @Operation(summary = "Returns message to which given tag is assigned")
    @GetMapping(value = "/tag")
    List<MessageDto> findMessagesByTag(@RequestParam("value") @NotNull(message = "missing tag param") String tag) {
        return messageUseCase.findMessagesByTag(tag);
    }

    @Operation(summary = "Returns messages which have the same original message given by id")
    @GetMapping(value = "/original-message")
    List<MessageDto> findMessagesByOriginalMessage(@RequestParam("value")
        @NotNull(message = "missing original message id param") Long originalMessageId) {
        return messageUseCase.findMessagesByOriginalMessage(originalMessageId);
    }

    @Operation(summary = "Returns messages which contains given content")
    @GetMapping(value = "/content")
    List<MessageDto> findMessagesByContent(@RequestParam("value")
    @NotNull(message = "missing content param") String content) {
        return messageUseCase.findMessagesByContent(content);
    }

    @Operation(summary = "Create message")

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createMessage(@RequestBody @Valid MessageDetails messageDetails) {
        messageUseCase.createMessage(messageDetails);
    }

    @Operation(summary = "Update message with given id")

    @PutMapping("/{id}")
    void updateMessage(@PathVariable("id") Long id, @RequestBody @Valid MessageDetails messageDetails) {
        messageUseCase.updateMessage(id, messageDetails);
    }

    @Operation(summary = "Delete message with given id")
    @DeleteMapping("/{id}")
    void deleteMessage(@PathVariable("id") Long id) {
        messageUseCase.deleteMessage(id);
    }
}
