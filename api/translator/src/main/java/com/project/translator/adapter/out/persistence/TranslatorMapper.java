package com.project.translator.adapter.out.persistence;

import com.project.translator.domain.LanguageDomain;
import com.project.translator.domain.MessageDomain;
import com.project.translator.domain.TagDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
interface TranslatorMapper {
    @Mapping(ignore = true, target = "messages")
    LanguageEntity toLanguageEntity(LanguageDomain languageDomain);
    LanguageDomain toLanguageDomain(LanguageEntity languageEntity);

    @Mapping(ignore = true, target = "messages")
    TagEntity toTagEntity(TagDomain tagDomain);
    TagDomain toTagDomain(TagEntity tagEntity);

    MessageEntity toMessageEntity(MessageDomain messageDomain);
    MessageDomain toMessageDomain(MessageEntity messageEntity);
}
