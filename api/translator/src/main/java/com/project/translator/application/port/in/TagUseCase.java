package com.project.translator.application.port.in;

import com.project.translator.application.port.out.TagDto;

import java.util.Collection;

public interface TagUseCase {

    Collection<TagDto> getTags();

    TagDto getTagById(Long id);

    void createTag(TagDetails tagDetails);

    void updateTag(Long id, TagDetails tagDetails);

    void deleteTag(Long id);
}
