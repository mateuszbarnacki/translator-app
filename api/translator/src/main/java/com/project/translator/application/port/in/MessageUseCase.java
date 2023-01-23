package com.project.translator.application.port.in;

import com.project.translator.application.port.out.MessageDto;

import java.util.Collection;
import java.util.List;

public interface MessageUseCase {
    Collection<MessageDto> getMessages();

    List<MessageDto> findMessagesByLanguage(String language);

    List<MessageDto> findMessagesByTag(String tag);

    List<MessageDto> findMessagesByOriginalMessage(Long originalMessageId);

    List<MessageDto> findMessagesByContent(String content);

    MessageDto getMessageById(Long id);

    void createMessage(MessageDetails messageDetails);

    void updateMessage(Long id, MessageDetails messageDetails);

    void deleteMessage(Long id);
}
