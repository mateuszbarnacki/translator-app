package com.project.translator.application.port.in;

import com.project.translator.domain.MessageDomain;

import java.util.Collection;
import java.util.List;

public interface MessageUseCase {
    Collection<MessageDomain> getMessages();

    List<MessageDomain> findMessagesByLanguage(String language);

    List<MessageDomain> findMessagesByTag(String tag);

    MessageDomain getMessageById(Long id);

    void createMessage(MessageDetails messageDetails);

    void updateMessage(Long id, MessageDetails messageDetails);

    void deleteMessage(Long id);
}
