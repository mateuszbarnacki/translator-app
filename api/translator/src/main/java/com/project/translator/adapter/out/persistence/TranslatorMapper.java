package com.project.translator.adapter.out.persistence;

import com.project.translator.domain.LanguageDomain;
import com.project.translator.domain.MessageDomain;
import com.project.translator.domain.TagDomain;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface TranslatorMapper {
    LanguageEntity toLanguageEntity(LanguageDomain languageDomain);
    LanguageDomain toLanguageDomain(LanguageEntity languageEntity);

    TagEntity toTagEntity(TagDomain tagDomain);
    TagDomain toTagDomain(TagEntity tagEntity);

    MessageEntity toMessageEntity(MessageDomain messageDomain);
    MessageDomain toMessageDomain(MessageEntity messageEntity);
}
