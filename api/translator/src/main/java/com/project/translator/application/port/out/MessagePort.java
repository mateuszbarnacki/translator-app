package com.project.translator.application.port.out;

import com.project.translator.application.port.in.MessageDetails;
import com.project.translator.domain.MessageDomain;

import java.util.Collection;
import java.util.List;

public interface MessagePort {

    Collection<MessageDomain> getMessages();

    List<MessageDomain> findMessageByLanguage(String language);

    List<MessageDomain> findMessageByTag(String tag);

    MessageDomain getMessageById(Long id);

    void createMessage(MessageDetails messageDetails);

    void updateMessage(Long id, MessageDetails messageDetails);

    void deleteMessage(Long id);
}
