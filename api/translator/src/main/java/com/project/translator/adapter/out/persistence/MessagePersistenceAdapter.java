package com.project.translator.adapter.out.persistence;

import com.project.common.PersistenceAdapter;
import com.project.translator.application.port.in.MessageDetails;
import com.project.translator.application.port.out.MessagePort;
import com.project.translator.domain.MessageDomain;
import com.project.translator.domain.exception.LanguageNotFoundException;
import com.project.translator.domain.exception.MessageNotFoundException;
import com.project.translator.domain.exception.OriginalMessageNotInEnglishException;
import com.project.translator.domain.exception.TagNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@PersistenceAdapter
class MessagePersistenceAdapter implements MessagePort {

    private final MessageRepository messageRepository;
    private final LanguageRepository languageRepository;
    private final TagRepository tagRepository;
    private final TranslatorMapper translatorMapper;
    private static final String ENGLISH_LANGUAGE = "English";

    @Override
    public Collection<MessageDomain> getMessages() {
        log.info("Loading all messages...");

        final var messageEntities = messageRepository.findAll();
        return messageEntities.stream()
                .map(translatorMapper::toMessageDomain)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDomain getMessageById(@NotNull Long id) {
        log.info("Loading message with id = <{}>", id);

        return translatorMapper.toMessageDomain(messageRepository.findById(id)
                .orElseThrow(() -> new MessageNotFoundException(id)));
    }

    @Override
    public void createMessage(@NotNull MessageDetails messageDetails) {
        if(messageDetails.original_message() == null) {
            createOriginalMessage(messageDetails);
        }
        else {
            createMessageTranslation(messageDetails);
        }
    }

    @Override
    public void updateMessage(@NotNull Long id, @NotNull MessageDetails messageDetails) {
        final var messageEntity = getMessageEntity(id);

        if(messageEntity.getOriginalMessage() == null){
            updateOriginalMessage(messageEntity, messageDetails);
        }
        else {
            updateMessageTranslation(messageEntity, messageDetails);
        }
    }

    @Override
    public void deleteMessage(@NotNull Long id) {
        log.info("Deleting message with id = <{}>", id);

        final var messageEntity = getMessageEntity(id);

        if(messageEntity.getOriginalMessage() == null) {
            final var messages = messageRepository.findByOriginalMessage(messageEntity);
            messageRepository.deleteAll(messages);
        }
        messageRepository.delete(messageEntity);
        log.info("Message deleted successfully with id = <{}>", id);
    }

    private void updateOriginalMessage(MessageEntity messageEntity, MessageDetails messageDetails) {
        log.info("Updating original message from message details = <{}>", messageDetails);

        final var originalMessageId = messageDetails.original_message();
        final var languageEntity = getLanguageEntity(messageDetails.language());
        if(originalMessageId != null) {
            throw new RuntimeException("Original message cannot contain original message id");
        }
        if(!isLanguageEnglish(languageEntity)) {
            throw new OriginalMessageNotInEnglishException(languageEntity.getLanguage());
        }
        final var tags = getTagEntities(messageDetails.tags());
        messageEntity.setContent(messageDetails.content());
        tags.forEach(messageEntity::addTag);

        log.info("Original message updated successfully = <{}>", messageEntity);
    }

    private void updateMessageTranslation(MessageEntity messageEntity, MessageDetails messageDetails) {
        log.info("Updating message translation from message details = <{}>", messageDetails);

        final var originalMessageId = messageDetails.original_message();
        final var languageEntity = getLanguageEntity(messageDetails.language());
        if(originalMessageId == null) {
            throw new RuntimeException("Message translation cannot be converted to original message");
        }
        final var originalMessage = getMessageEntity(originalMessageId);
        final var tags = new HashSet<>(originalMessage.getTags());

        messageEntity.setOriginalMessage(originalMessage);
        messageEntity.setLanguage(languageEntity);
        messageEntity.setContent(messageDetails.content());
        tags.forEach(messageEntity::addTag);

        log.info("Message translation updated successfully = <{}>", messageEntity);
    }


    private void createOriginalMessage(MessageDetails messageDetails) {
        final var languageId = messageDetails.language();
        final var languageEntity = getLanguageEntity(languageId);
        if(isLanguageEnglish(languageEntity)) {
            createValidOriginalMessage(messageDetails, languageEntity);
        }
        else {
            throw new OriginalMessageNotInEnglishException(languageEntity.getLanguage());
        }
    }

    private void createValidOriginalMessage(MessageDetails messageDetails, LanguageEntity languageEntity) {
        log.info("Creating original message from message details = <{}>", messageDetails);

        final var tagIds = messageDetails.tags();
        final var tags = getTagEntities(tagIds);
        final var messageEntity = new MessageEntity();
        messageEntity.setLanguage(languageEntity);
        messageEntity.setContent(messageDetails.content());
        tags.forEach(messageEntity::addTag);
        messageRepository.save(messageEntity);

        log.info("Original message created successfully = <{}>", messageEntity);
    }

    private void createMessageTranslation(MessageDetails messageDetails) {
        log.info("Creating message translation from message details = <{}>", messageDetails);

        final var languageId = messageDetails.language();
        final var originalMessageId = messageDetails.original_message();
        final var originalMessage = getMessageEntity(originalMessageId);
        final var tags = new HashSet<>(originalMessage.getTags());

        final var messageEntity = new MessageEntity();
        messageEntity.setOriginalMessage(originalMessage);
        messageEntity.setContent(messageDetails.content());
        messageEntity.setLanguage(getLanguageEntity(languageId));
        tags.forEach(messageEntity::addTag);
        messageRepository.save(messageEntity);

        log.info("Message translation created successfully = <{}>", messageEntity);
    }

    private LanguageEntity getLanguageEntity(Long languageId) {
        return languageRepository.findById(languageId)
                .orElseThrow(() -> new LanguageNotFoundException(languageId));
    }

    private TagEntity getTagEntity(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException(tagId));
    }

    private Set<TagEntity> getTagEntities(List<Long> tagIds) {
        final var tagEntities = new HashSet<TagEntity>();
        if(tagIds != null) {
            tagIds.forEach(id -> tagEntities.add(getTagEntity(id)));
        }
        return tagEntities;
    }

    private MessageEntity getMessageEntity(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException(messageId));
    }

    private boolean isLanguageEnglish(LanguageEntity languageEntity) {
        return languageEntity.getLanguage().equals(ENGLISH_LANGUAGE);
    }
}
