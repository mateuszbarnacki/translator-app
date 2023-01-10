package com.project.translator.application.port.out;

import com.project.translator.application.port.in.MessageDetails;
import com.project.translator.domain.MessageDomain;

import java.util.Collection;

public interface MessagePort {

    Collection<MessageDomain> getMessages();

    MessageDomain getMessageById(Long id);

    void createMessage(MessageDetails messageDetails);

    void updateMessage(Long id, MessageDetails messageDetails);

    void deleteMessage(Long id);
}
