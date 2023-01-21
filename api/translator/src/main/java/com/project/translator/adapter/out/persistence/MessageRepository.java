package com.project.translator.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAll();

    List<MessageEntity> findByTagsId(Long id);

    List<MessageEntity> findByLanguage_languageContainingIgnoreCase(String language);

    List<MessageEntity> findByContentContainingIgnoreCase(String content);

    Optional<MessageEntity> findByOriginalMessageId(Long id);

    List<MessageEntity> findByOriginalMessage(MessageEntity originalMessage);
}
