package com.project.translator.adapter.out.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RepositoryTest {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private TagRepository tagRepository;

    @BeforeAll
    void setUp() {
        TagEntity tag = RandomEntityFactory.generateTag("testTag");
        tagRepository.save(tag);
        MessageEntity message = RandomEntityFactory.generateMessage();
        message.addTag(tag);

        LanguageEntity language = RandomEntityFactory.generateLanguage("testLanguage");
        language.addMessage(message);

        languageRepository.save(language);
        messageRepository.save(message);
    }


    @Test
    void findMessagesByLanguage() {
        // given
        // add another message with same language
        TagEntity tag = RandomEntityFactory.generateTag("testTag");
        MessageEntity message = RandomEntityFactory.generateMessage();
        LanguageEntity language = RandomEntityFactory.generateLanguage("testLanguage");

        saveEntities(tag, language, message);

        // when
        List<MessageEntity> messageEntities = messageRepository.findByLanguage_languageContainingIgnoreCase(
                "testLanguage");
        // then
        assertThat(messageEntities).hasSize(2);
    }

    @Test
    void findMessagesByTagId() {
        // given
        Optional<TagEntity> tag = tagRepository.findByTag("testTag");
        // when
        List<MessageEntity> messages = messageRepository.findByTagsId(tag.get().getId());
        // then
        assertThat(messages).hasSize(1);
    }

    @Test
    void findMessagesByContent() {
        // given
        MessageEntity message = RandomEntityFactory.generateMessage();
        message.setContent("Very important message");
        messageRepository.save(message);
        // when
        List<MessageEntity> messageEntities = messageRepository.findByContentContainingIgnoreCase("important");
        // then
        assertThat(messageEntities).containsOnly(message);
    }

    @Test
    void findMessagesByOriginalMessage() {
        // given
        // add base message
        MessageEntity baseMessage = RandomEntityFactory.generateMessage();
        // add child message
        MessageEntity childMessage = RandomEntityFactory.generateMessage();
        childMessage.setOriginalMessage(baseMessage);

        messageRepository.save(baseMessage);
        messageRepository.save(childMessage);
        // when
        Optional<MessageEntity> message = messageRepository.findByOriginalMessageId(baseMessage.getId());
        // then
        assertThat(message.get()).isEqualTo(childMessage);
    }

    private void saveEntities(TagEntity tag, LanguageEntity language, MessageEntity message) {
        message.addTag(tag);
        language.addMessage(message);

        languageRepository.save(language);
        messageRepository.save(message);
    }
}