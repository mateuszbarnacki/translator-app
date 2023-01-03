package com.project.translator.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAll();

    List<MessageEntity> findByTagsId(Long id);

    @Query("select m from MessageEntity m join LanguageEntity l on m.language.id = l.id where l.language = :language")
    List<MessageEntity> findByLanguage(String language);

    List<MessageEntity> findByContentContainingIgnoreCase(String content);

    Optional<MessageEntity> findByOriginalMessageId(Long id);
}
