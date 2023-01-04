package com.project.translator.adapter.out.persistence;

import com.project.common.PersistenceAdapter;
import com.project.translator.application.port.in.TagDetails;
import com.project.translator.application.port.out.TagPort;
import com.project.translator.domain.exception.TagNotFoundException;
import com.project.translator.domain.TagDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@PersistenceAdapter
class TagPersistenceAdapter implements TagPort {
    
    private final TagRepository tagRepository;
    
    private final TranslatorMapper translatorMapper;
    
    @Override
    public Collection<TagDomain> getTags() {
        log.info("Loading all tags...");

        final var tagEntities = tagRepository.findAll();
        return tagEntities.stream()
                .map(translatorMapper::toTagDomain)
                .collect(Collectors.toList());
    }

    @Override
    public TagDomain getTagById(Long id) {
        log.info("Loading tag with id = <{}>", id);

        return translatorMapper.toTagDomain(tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException(id)));
    }

    @Override
    public void createTag(TagDetails tagDetails) {
        log.info("Creating tag from tag details = <{}>", tagDetails);

        final var tagEntity = new TagEntity();
        tagEntity.setTag(tagDetails.tag());
        tagRepository.save(tagEntity);

        log.info("Tag created successfully = <{}>", tagEntity);
    }

    @Override
    public void updateTag(Long id, TagDetails tagDetails) {
        log.info("Updating tag from tag details = <{}> with id = <{}>",
                tagDetails, id);

        final var tagEntity = getTagEntity(id);
        tagEntity.setTag(tagDetails.tag());

        log.info("Tag updated successfully = <{}>", tagEntity);
    }

    @Override
    public void deleteTag(Long id) {
        log.info("Deleting tag with id = <{}>", id);

        final var tagEntity = getTagEntity(id);
        final var messages = tagEntity.getMessages();

        messages.forEach(
                message -> message.getTags().remove(tagEntity)
        );

        tagRepository.delete(tagEntity);

        log.info("Tag deleted successfully with id = <{}>", id);
    }

    private TagEntity getTagEntity(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException(id));
    }
}
