package com.project.translator.application.service;

import com.project.common.UseCase;
import com.project.translator.application.port.in.MessageDetails;
import com.project.translator.application.port.in.MessageUseCase;
import com.project.translator.application.port.out.MessageDto;
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
    private final MessageMapper messageMapper;

    @Override
    public Collection<MessageDto> getMessages() {
        Collection<MessageDomain> messages = messagePort.getMessages();
        return messageMapper.mapDomainsToDtos(messages);
    }

    @Override
    public List<MessageDto> findMessagesByLanguage(String language) {
        Collection<MessageDomain> messages = messagePort.findMessageByLanguage(language);
        return messageMapper.mapDomainsToDtos(messages).stream().toList();
    }

    @Override
    public List<MessageDto> findMessagesByTag(String tag) {
        Collection<MessageDomain> messages = messagePort.findMessageByTag(tag);
        return messageMapper.mapDomainsToDtos(messages).stream().toList();
    }

    @Override
    public List<MessageDto> findMessagesByOriginalMessage(Long originalMessageId){
        Collection<MessageDomain> messages = messagePort.findMessageByOriginalMessage(originalMessageId);
        return messageMapper.mapDomainsToDtos(messages).stream().toList();
    }

    @Override
    public List<MessageDto> findMessagesByContent(String content) {
        Collection<MessageDomain> messages = messagePort.findMessagesByContent(content);
        return messageMapper.mapDomainsToDtos(messages).stream().toList();
    }

    @Override
    public MessageDto getMessageById(Long id) {
        MessageDomain message = messagePort.getMessageById(id);
        return messageMapper.mapDomainToDto(message);
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
