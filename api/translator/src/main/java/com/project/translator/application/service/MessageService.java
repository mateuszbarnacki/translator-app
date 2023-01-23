package com.project.translator.application.service;

import com.project.common.UseCase;
import com.project.translator.application.port.in.MessageDetails;
import com.project.translator.application.port.in.MessageUseCase;
import com.project.translator.application.port.out.MessageDto;
import com.project.translator.application.port.out.MessageMapper;
import com.project.translator.application.port.out.MessagePort;
import com.project.translator.domain.exception.MessageNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class MessageService implements MessageUseCase {
    private final MessagePort messagePort;
    private final MessageMapper messageMapper;

    @Override
    public Collection<MessageDto> getMessages() {
        return messagePort.getMessages().stream()
                .map(messageMapper::mapDomainToDto)
                .toList();
    }

    @Override
    public List<MessageDto> findMessagesByLanguage(String language) {
        return messagePort.findMessageByLanguage(language).stream()
                .map(messageMapper::mapDomainToDto)
                .toList();
    }

    @Override
    public List<MessageDto> findMessagesByTag(String tag) {
        return messagePort.findMessageByTag(tag).stream()
                .map(messageMapper::mapDomainToDto)
                .toList();
    }

    @Override
    public List<MessageDto> findMessagesByOriginalMessage(Long originalMessageId){
     return messagePort.findMessageByOriginalMessage(originalMessageId).stream()
             .map(messageMapper::mapDomainToDto)
             .toList();
    }

    @Override
    public List<MessageDto> findMessagesByContent(String content) {
        return messagePort.findMessagesByContent(content).stream()
                .map(messageMapper::mapDomainToDto)
                .toList();
    }

    @Override
    public MessageDto getMessageById(Long id) {
        return Optional.ofNullable(id)
                .map(messagePort::getMessageById)
                .map(messageMapper::mapDomainToDto)
                .orElseThrow(() -> new MessageNotFoundException(id));
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
