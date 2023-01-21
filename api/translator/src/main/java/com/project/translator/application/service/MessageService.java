package com.project.translator.application.service;

import com.project.common.UseCase;
import com.project.translator.application.port.in.MessageDetails;
import com.project.translator.application.port.in.MessageUseCase;
import com.project.translator.application.port.out.MessagePort;
import com.project.translator.domain.MessageDomain;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@UseCase
@Transactional
public class MessageService implements MessageUseCase {

    private final MessagePort messagePort;

    @Override
    public Collection<MessageDomain> getMessages() {
        return messagePort.getMessages();
    }

    @Override
    public List<MessageDomain> findMessagesByLanguage(String language) {
        return messagePort.findMessageByLanguage(language);
    }

    @Override
    public MessageDomain getMessageById(Long id) {
        return messagePort.getMessageById(id);
    }

    @Override
    public void createMessage(MessageDetails messageDetails) {
        messagePort.createMessage(messageDetails);
    }

    @Override
    public void updateMessage(Long id, MessageDetails messageDetails) {
        messagePort.updateMessage(id, messageDetails);
    }

    @Override
    public void deleteMessage(Long id) {
        messagePort.deleteMessage(id);
    }
}
