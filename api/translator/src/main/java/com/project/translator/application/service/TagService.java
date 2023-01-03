package com.project.translator.application.service;

import com.project.common.UseCase;
import com.project.translator.application.port.in.TagDetails;
import com.project.translator.application.port.in.TagUseCase;
import com.project.translator.application.port.out.TagPort;
import com.project.translator.domain.TagDomain;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
@UseCase
@Transactional
public class TagService implements TagUseCase {

    private final TagPort tagPort;

    @Override
    public Collection<TagDomain> getTags() {
        return tagPort.getTags();
    }

    @Override
    public TagDomain getTagById(Long id) {
        return tagPort.getTagById(id);
    }

    @Override
    public void createTag(TagDetails tagDetails) {
        tagPort.createTag(tagDetails);
    }

    @Override
    public void updateTag(Long id, TagDetails tagDetails) {
        tagPort.updateTag(id, tagDetails);
    }

    @Override
    public void deleteTag(Long id) {
        tagPort.deleteTag(id);
    }
}
