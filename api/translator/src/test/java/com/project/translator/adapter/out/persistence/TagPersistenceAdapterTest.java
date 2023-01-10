package com.project.translator.adapter.out.persistence;

import com.project.translator.application.port.in.TagDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Import({TagPersistenceAdapter.class})
public class TagPersistenceAdapterTest {

    private static final String NOTE_TAG = "Note";
    private static final String MESSAGE_TAG = "Message";

    @Autowired
    private TagPersistenceAdapter adapter;

    @Autowired
    private TagRepository tagRepository;

    @MockBean
    private TranslatorMapper translatorMapper;

    @BeforeEach
    void setup() {
        adapter.createTag(createTagDetails(NOTE_TAG));
    }

    @Test
    void tagShouldBeCreatedSuccessfully() {
        assertThat(tagRepository.findAll()).hasSize(1);
    }

    @Test
    void tagShouldBeUpdatedSuccessfully() {
        final var tagEntities = tagRepository.findAll();
        assertEquals(NOTE_TAG, tagEntities.get(0).getTag());

        adapter.updateTag(tagEntities.get(0).getId(),
                createTagDetails(MESSAGE_TAG));
        final var updatedTagEntity = tagRepository.findAll();
        assertEquals(updatedTagEntity.get(0).getTag(), MESSAGE_TAG);
    }

    @Test
    void tagShouldBeDeletedSuccessfully() {
        assertThat(tagRepository.findAll()).hasSize(1);
        adapter.deleteTag(tagRepository.findAll().get(0).getId());
        assertThat(tagRepository.findAll()).hasSize(0);
    }

    @Test
    void shouldFindAllTags() {
        adapter.createTag(createTagDetails(MESSAGE_TAG));
        assertThat(adapter.getTags()).hasSize(2);
    }

    private TagDetails createTagDetails(String tag) {
        return new TagDetails(tag);
    }
}
