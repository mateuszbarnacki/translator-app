package com.project.translator.application.service;

import com.project.common.UseCase;
import com.project.translator.application.port.in.TagDetails;
import com.project.translator.application.port.in.TagUseCase;
import com.project.translator.application.port.out.TagDto;
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
    private final TagMapper tagMapper;

    @Override
    public Collection<TagDto> getTags() {
        Collection<TagDomain> tags = tagPort.getTags();
        return tagMapper.mapDomainsToDtos(tags);
    }

    @Override
    public TagDto getTagById(Long id) {
        TagDomain tag = tagPort.getTagById(id);
        return tagMapper.mapDomainToDto(tag);
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
