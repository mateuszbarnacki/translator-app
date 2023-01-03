package com.project.translator.application.port.in;

import com.project.translator.domain.MessageDomain;

import java.util.Collection;

public interface MessageUseCase {
    Collection<MessageDomain> getMessages();

    MessageDomain getMessageById(Long id);

    void createMessage(MessageDetails messageDetails);

    void updateMessage(Long id, MessageDetails messageDetails);

    void deleteMessage(Long id);
}
