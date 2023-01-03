package com.project.translator.application.port.out;

import com.project.translator.application.port.in.TagDetails;
import com.project.translator.domain.TagDomain;

import java.util.Collection;

public interface TagPort {

    Collection<TagDomain> getTags();

    TagDomain getTagById(Long id);

    void createTag(TagDetails tagDetails);

    void updateTag(Long id, TagDetails tagDetails);

    void deleteTag(Long id);
}
