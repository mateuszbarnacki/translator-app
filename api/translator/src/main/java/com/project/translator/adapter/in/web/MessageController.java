package com.project.translator.adapter.in.web;

import com.project.translator.application.port.in.MessageDetails;
import com.project.translator.application.port.in.MessageUseCase;
import com.project.translator.domain.MessageDomain;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageUseCase messageUseCase;

    @GetMapping
    Collection<MessageDomain> getMessages() {
        return messageUseCase.getMessages();
    }

    @GetMapping(value = "/{id}")
    MessageDomain getById(@PathVariable("id") Long id) {
        return messageUseCase.getMessageById(id);
    }


    @GetMapping(value = "/search/language")
    List<MessageDomain> findMessagesByLanguage(@RequestParam("value") @NotNull(message = "missing language param") String language) {
        return messageUseCase.findMessagesByLanguage(language);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createMessage(@RequestBody @Valid MessageDetails messageDetails) {
        messageUseCase.createMessage(messageDetails);
    }

    @PutMapping("/{id}")
    void updateMessage(@PathVariable("id") Long id, @RequestBody @Valid MessageDetails messageDetails) {
        messageUseCase.updateMessage(id, messageDetails);
    }

    @DeleteMapping("/{id}")
    void deleteMessage(@PathVariable("id") Long id) {
        messageUseCase.deleteMessage(id);
    }
}
